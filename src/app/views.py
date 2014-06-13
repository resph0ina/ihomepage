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
    if not session.has_key('ihomepage') or session['ihomepage'] == None or True:
        block.Block.uid = 0
        b1 = block.Block('textlines')
        # b1.name = 'weather.simple'
        b1.name = 'baidu.hotword'
        b1.width = 1
        b2 = block.Block('textlines')
        # b2.name = 'renren.status'
        b2.name = 'douban.recent'
        b2.width = 3
        b2.height = 1
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
                           blocks = blocks, names = app.config['BLOCKNAME'])

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
    blocks = g.blocks
    #add
    if (request.form.has_key('add')):
        flash("add success")
    #upload
    elif (request.form.has_key('upload')):
        uploadfiles = request.files['file']
        for f in uploadfiles:
            flash('upload success')
            session['ihomepage'] = f
    blocks = json.loads(session['ihomepage'])
    return render_template('setting.html',
                           session = session,
                           blocks = blocks)

@app.route('/settingmodify/<blockId>', methods = ['GET', 'POST'])
def settingmodify(blockId):
    print blockId
    blocks = g.blocks
    which = 0
    for i in range(0, len(blocks)):
        if blocks[i].uid == int(blockId):
            which = i
    #change
    if (request.form.has_key('change')):
        flash("change success")
        blocks[which].width = int(request.form.get('width'))
        blocks[which].height = int(request.form.get('height'))
    #delete    
    elif (request.form.has_key('delete')):
        flash("delete success")
        del blocks[which]
    print session['ihomepage']
    ss = []
    for i in blocks:
        ss.append(object2dict(i))
    session['ihomepage'] = json.dumps(ss)
    return redirect('/setting')

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

