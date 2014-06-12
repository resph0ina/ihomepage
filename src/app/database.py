from app import db, models

def checkRegister(newuser):
    user = models.User.query.filter_by(username=newuser['username']).first()
    if user != None:
        return 'username exists'
    if len(newuser['password']) < 6:
        return 'password too short'
    if (newuser['password'] != newuser['verifypassword']):
        return 'input your password again correctly'
    return 'success'

def addUser(username, password):
    user_new = models.User(username, password)
    db.session.add(user_new)
    db.session.commit()
    return 'success'

def login(username, password):
    name = username
    user = models.User.query.filter_by(username=name).first()
    if user == None:
        return 'user not exist'
    elif password != user.password:
        return 'wrong password'
    return 'success'

def modifysetting(username, setting):
    user = models.User.query.filter_by(username=username).first()
    user.homepage = setting
    db.session.add(user)
    db.session.commit()
    return 'success'

def getuserbyname(username):
    user = models.User.query.filter_by(username=username).first()
    return user

