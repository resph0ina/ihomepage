from flask import Flask, render_template, flash, redirect, session, request
from app import app, database

@app.route('/')
@app.route('/ihomepage', methods = ['GET', 'POST'])
def ihomepage():
    return render_template('ihomepage.html',
                           username = session['username'])
    
@app.route('/register', methods = ['GET', 'POST'])
def register():
    #user submit
    if (request.form.has_key('username')):
        username = request.form.get('username')
        password = request.form.get('password')
        result = database.checkRegister(username, password)
        if result == 'success':
            session['username'] = username
            database.addUser(username, password)
            return redirect('/registersuccess')
        else:
            return render_template("register.html")+"<br>"+result
    #user click
    return render_template("register.html")

@app.route('/login', methods = ['GET', 'POST'])
def login():
    #user submit
    if (request.form.has_key('username')):
        username = request.form.get('username')
        password = request.form.get('password')
        result = database.login(username, password)
        if result == 'success':
            session['username'] = username
            return redirect('/index')
        else:
            return render_template("login.html")+"<br>"+result
    #user click
    return render_template("login.html")


@app.route('/registersuccess', methods = ['GET', 'POST'])
def registersuccess():
    return 'register success'

