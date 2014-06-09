from flask import Flask, session
from flask.ext.sqlalchemy import SQLAlchemy

app = Flask(__name__)
app.secret_key = "hatsune miku"
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:////tmp/test.db'
db = SQLAlchemy(app)

from app import views, models

