from flask import Flask, render_template, flash, redirect, session, request, g, make_response
from flask_wtf import Form
from wtforms import TextField
from wtforms.validators import DataRequired
import json
from utils import *

class ImageSetting(Form):
    src = TextField('src')

class Image:
    def __init__(self):
        a = 0
        
    def render(self, blockId):
        blocks = g.blocks
        which = 0
        for i in range(0, len(blocks)):
            if blocks[i].uid == int(blockId[1:]):
                which = i
        urls = json.loads(session['ihomepage'])[which]['config']
        return render_template("Image.html", urls = urls)

    def getform(self):
        form = ImageSetting()
        return form

    def rendersetting(self, blockId):
        blocks = g.blocks
        which = 0
        for i in range(0, len(blocks)):
            if blocks[i].uid == int(blockId[1:]):
                which = i
        urls = json.loads(session['ihomepage'])[which]['config']
        return render_template("ImageSetting.html", urls = urls, blockId = blockId, num = len(urls))

    def applysetting(self, blockId):
        fetch = [i for i in g.blocks if i.uid == int(blockId[1:])]
        if len(fetch) == 0 : return "Can't find block in session!"
        b = fetch[0]
        blocks = g.blocks
        which = 0
        for i in range(0, len(blocks)):
            if blocks[i].uid == int(blockId[1:]):
                which = i
        #urls = json.loads(blocks[i].config)
        urls = json.loads(session['ihomepage'])[which]['config']
        if (request.form.has_key('change')):
            for i in range(0, len(urls)):
                urls[i] = request.form.get('src_'+str(i))
            blocks[which].config = urls
            ss = []
            for i in blocks:
                ss.append(object2dict(i))
            session['ihomepage'] = json.dumps(ss)
            return 'success'
        elif (request.form.has_key('add')):
            urls.append(request.form.get('srcadd'))
            blocks[which].config = urls
            ss = []
            for i in blocks:
                ss.append(object2dict(i))
            session['ihomepage'] = json.dumps(ss)
            return 'success'
        return 'fail'
            
