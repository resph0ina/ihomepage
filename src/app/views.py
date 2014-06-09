from flask import Flask, render_template, flash, redirect, session, request
from app import app, database

@app.route('/')
@app.route('/index')
def index():
    return redirect('/register')
    
@app.route('/register', methods = ['GET', 'POST'])
def register():
    #user submit
    if (request.form.has_key('username')):
        username = request.form.get('username')
        password = request.form.get('password')
        result = database.checkRegister(username, password)
        if result == 'success':
            session['username'] = username
            return redirect('/registersuccess')
        else:
            return render_template("register.html")+"<br>"+result
    #user click
    return render_template("register.html")


@app.route('/registersuccess', methods = ['GET', 'POST'])
def registersuccess():
    return 'register success'
