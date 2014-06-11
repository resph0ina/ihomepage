# -*- coding: utf-8 -*-
from flask import Flask, render_template, flash, redirect, session, request, g
from app import app, database, block
import json

@app.before_request
def before_request():
    g.blocks = json.loads(session['ihomepage'])


@app.route('/')
@app.route('/ihomepage', methods = ['GET', 'POST'])
def ihomepage():
    if not session.has_key('ihomepage') or True:
        block.Block.uid = 0
        b1 = block.Block(20,10,'test')
        b1.width = 1
        b2 = block.Block(20,10,'test')
        b2.width = 2
        b2.height = 2
        b2.color='green'
        default_blocks = [b1,b2]
        session['ihomepage'] = json.dumps(default_blocks, default = block.object2dict)

    blocks = json.loads(session['ihomepage'])
    return render_template('ihomepage.html',
                           session = session,
                           blocks = blocks)
    
@app.route('/register', methods = ['GET', 'POST'])
def register():
    #user submit
    if (request.form.has_key('username')):
        newuser = {}
        newuser['username'] = request.form.get('username')
        newuser['password'] = request.form.get('password')
        newuser['verifypassword'] = request.form.get('verifypassword')
        result = database.checkRegister(newuser)
        if result == 'success':
            session['username'] = newuser['username']
            database.addUser(newuser['username'], newuser['password'])
            return redirect('/registersuccess')
        else:
            return render_template("register.html", session = session)+"<br>"+result
    #user click
    return render_template("register.html", session = session)

@app.route('/login', methods = ['GET', 'POST'])
def login():
    #user submit
    if (request.form.has_key('username')):
        username = request.form.get('username')
        password = request.form.get('password')
        result = database.login(username, password)
        if result == 'success':
            session['username'] = username
            return redirect('/ihomepage')
        else:
            return render_template("login.html", session = session)+"<br>"+result
    #user click
    return render_template("login.html",
                           session = session)

@app.route('/logout', methods = ['GET', 'POST'])
def logout():
    del(session['username'])
    return redirect('ihomepage')

@app.route('/setting', methods = ['GET', 'POST'])
def setting():
    blocks = json.loads(session['ihomepage'])
    maxuid = 0
    idmap = {}
    num = 0
    for bk in blocks:
        if bk['uid'] > maxuid:
            maxuid = bk['uid']
            idmap[bk['uid']] = num
            num = num+1
    postfix = ""
    if (request.form.has_key('change')):
        postfix = "<br>all your change is saved successfully"
    else:
        for i in range(1, maxuid+1):
            if (request.form.has_key('change'+str(i))):
                postfix = "<br>your change is saved successfully"+str(i)
            if (request.form.has_key('delete'+str(i))):
                del blocks[idmap[i]]
                session['ihomepage'] = json.dumps(blocks, default = block.object2dict)
                postfix = "<br>delete successfully"+str(i)
    blocks = json.loads(session['ihomepage'])
    return render_template('setting.html',
                           session = session,
                           blocks = blocks)+postfix


@app.route('/registersuccess', methods = ['GET', 'POST'])
def registersuccess():
    return 'register success'

