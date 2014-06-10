from flask import Flask, session
from flask.ext.sqlalchemy import SQLAlchemy

app = Flask(__name__)
app.secret_key = "hatsune miku"
app.config.from_object('config')
db = SQLAlchemy(app)

from app import views, models

