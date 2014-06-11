from flask import Flask, render_template, flash, redirect, session, request, g
from app import app, database, block
import json

@app.route('/getblock/<blockId>', methods = ['GET', 'POST'])
def getblock(blockId):
	# b = block.Block(1,1,'test')
	b = g.blocks[int(blockId[1:]) - 1]
	return render_template('block_textlines.html', block = b)