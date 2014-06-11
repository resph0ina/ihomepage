from flask import Flask, render_template, flash, redirect, session, request
from app import app, database, block
import json

@app.route('/')
@app.route('/ihomepage', methods = ['GET', 'POST'])
def ihomepage():
    if not session.has_key('ihomepage') or True:
        default_blocks = block.initial_blocks
        b1 = block.Block(20,10,'test')
        b1.width = 1
        b2 = block.Block(20,10,'test')
        b2.width = 2
        b2.height = 2
        b2.color='green'
        default_blocks.append(b1)
        default_blocks.append(b2)
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


@app.route('/registersuccess', methods = ['GET', 'POST'])
def registersuccess():
    return 'register success'

