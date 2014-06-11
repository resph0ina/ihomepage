from flask import Flask, render_template, flash, redirect, session, request, g
from app import app, database, block
from modules import *
import json

services = {'baidu.news': TestGrabber.TestGrabber()}

@app.route('/getblock/<blockId>', methods = ['GET', 'POST'])
def getblock(blockId):
	# b = block.Block(1,1,'test')
	b = g.blocks[int(blockId[1:]) - 1]
	if services.has_key(b.name):
		b.content['raw'] = getservice(b.name)
	else:
		b.content['raw'] = 'Default'
	return render_template('block_textlines.html', block = b)

@app.route('/service/')
def infoservice():
	return 'Supported services: ' + repr(services.keys());

@app.route('/service/<name>', methods = ['GET', 'POST'])
def getservice(name):
	if services.has_key(name):
		grabber = services[name]
		return grabber.grab()
	else:
		return infoservice()