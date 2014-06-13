# -*- coding: utf-8 -*-
from flask import Flask, render_template, flash, redirect, session, request, g, make_response
from app import app, database, block
from flask_wtf import Form
from wtforms import TextField
from wtforms.validators import DataRequired
from modules import *
from tools import *
import json, urllib2

services = {'baidu.news': TestGrabber.TestGrabber(), 'renren.status': RenrenGrabber.RenrenGrabber()}
tools = {'image': Image.Image(), 'index': Index.Index()}

@app.route('/getsetting/<blockId>', methods = ['GET', 'POST'])
def getsetting(blockId):
    fetch = [i for i in g.blocks if i.uid == int(blockId[1:])]
    if len(fetch) == 0 : return "Can't find block in session!"
    b = fetch[0]
    print b.name
    b.config = str(b.config)
    if services.has_key(b.name):
        if Service.Service().applysetting(blockId) == 'success':
            flash('success')
            return redirect('/setting')
        return Service.Service().rendersetting(blockId)
    elif tools.has_key(b.name):
        if tools[b.name].applysetting(blockId) == 'success':
            flash('success')
            return redirect('/setting')
        return tools[b.name].rendersetting(blockId)
    else:
        return ''
    return ''
