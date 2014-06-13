# -*- coding: utf-8 -*-
from flask import Flask, render_template, flash, redirect, session, request, g, make_response
from app import app, database, block
from utils import *
import json
import types

@app.before_request
def before_request():
    if session.has_key('ihomepage') and session['ihomepage'] != None:
        g.blocks = [dict2object(i) for i in json.loads(session['ihomepage'])]


@app.route('/')
@app.route('/ihomepage', methods = ['GET', 'POST'])
def ihomepage():
    if not session.has_key('ihomepage') or session['ihomepage'] == None:
        block.Block.uid = 0
        b1 = block.Block('textlines')
        b1.name = 'weather.simple'
        b1.width = 1
        b2 = block.Block('textlines')
        b2.name = 'renren.status'
        b2.width = 2
        b2.height = 2
        b2.color='green'
        b3 = block.Block('textlines')
        b3.name = 'image'
        b3.width = 2
        b3.height = 2
        b3.config = ["a"]
        default_blocks = [b1,b2,b3]
        session['ihomepage'] = json.dumps(default_blocks, default = object2dict)

    blocks = json.loads(session['ihomepage'])
    return render_template('ihomepage.html',
                           session = session,
                           blocks = blocks)

@app.route('/downloadsetting', methods = ['GET', 'POST'])
def downloadsetting():
    response = make_response(session['ihomepage'])
    response.headers['Content-Type']='application/octet-stream'
    response.headers['Content-Disposition']='attachment; filename=setting.sd'
    return response

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
    #change
    if (request.form.has_key('change')):
        postfix = "<br>all your change is saved successfully"
        for i in request.form.keys():
            if len(i.split('_')) <= 1:
                continue
            key = i.split('_')[0]
            uid = i.split('_')[1]
            if type(blocks[idmap[int(uid)]][key]) is types.IntType:
                blocks[idmap[int(uid)]][key] = int(request.form[i])
            else:
                blocks[idmap[int(uid)]][key] = request.form[i]
            session['ihomepage'] = json.dumps(blocks, default = object2dict)
    #add
    elif (request.form.has_key('add')):
        postfix = "<br>add successfully"
        newblock = block.Block("test")
        newbk = object2dict(newblock)
        for i in request.form.keys():
            if (newbk.has_key(i)):
                if request.form.get(i) == "":
                    continue
                if type(newbk[i]) is types.IntType:
                    newbk[i] = int(request.form.get(i))
                else:
                    newbk[i] = request.form.get(i)
        blocks.append(newbk)
        session['ihomepage'] = json.dumps(blocks, default = object2dict)
    #upload
    elif (request.form.has_key('upload')):
        postfix = "<br>upload success"
        uploadfiles = request.files['file']
        print uploadfiles
        for f in uploadfiles:
            session['ihomepage'] = f
    #delete    
    else:
        for i in range(1, maxuid+1):
            if (request.form.has_key('delete_'+str(i))):
                del blocks[idmap[i]]
                session['ihomepage'] = json.dumps(blocks, default = object2dict)
                postfix = "<br>delete successfully"+str(i)
    print session['ihomepage']
    blocks = json.loads(session['ihomepage'])
    return render_template('setting.html',
                           session = session,
                           blocks = blocks)+postfix

@app.route('/dbupload', methods = ['GET', 'POST'])
def dbupload():
    database.modifysetting(session['username'], session['ihomepage'])
    return redirect('/setting')

@app.route('/dbdownload', methods = ['GET', 'POST'])
def dbdownload():
    user = database.getuserbyname(session['username'])
    session['ihomepage'] = user.homepage
    return redirect('/setting')

@app.route('/registersuccess', methods = ['GET', 'POST'])
def registersuccess():
    return 'register success'

