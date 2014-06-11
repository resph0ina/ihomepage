from flask import Flask
from flask.ext.sqlalchemy import SQLAlchemy
from app import db

class User(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(80), unique=True)
    password = db.Column(db.String(120), unique=False)
    email = db.Column(db.String(80), unique=True)
    homepage = db.Column(db.String(1000), unique=False)
    image = db.Column(db.String(1000), unique=False)

    def __init__(self, username, password):
        self.username = username
        self.password = password

    def __repr__(self):
        return '<User %r>' % self.username
