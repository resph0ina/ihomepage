from flask import Flask, render_template, flash, redirect, session, request, g, make_response
from flask_wtf import Form
from wtforms import TextField
from wtforms.validators import DataRequired
import json
from utils import *

class Service:
    def __inti__(self):
        a = 0

    def rendersetting(self, blockId):
        blocks = g.blocks
        which = 0
        for i in range(0, len(blocks)):
            if blocks[i].uid == int(blockId[1:]):
                which = i
        config = json.loads(session['ihomepage'])[which]['config']
        print config
        username = ""
        password = ""
        custom = ""
        if config.has_key('username'):
            username = config['username']
        if config.has_key('password'):
            password = config['password']
        if config.has_key('custom'):
            custom = config['custom']
        return render_template("ServiceSetting.html", username = username, password = password, custom = custom, blockId = blockId, which = which)

    def applysetting(self, blockId):
        blocks = g.blocks
        which = 0
        for i in range(0, len(blocks)):
            if blocks[i].uid == int(blockId[1:]):
                which = i
        if (request.form.has_key('change')):
            config = {}
            config['username'] = request.form.get('username')
            config['password'] = request.form.get('password')
            config['custom'] = request.form.get('custom')
            blocks[which].config = config
            ss = []
            for i in blocks:
                ss.append(object2dict(i))
            session['ihomepage'] = json.dumps(ss)
            return 'success'
        return 'fail'
