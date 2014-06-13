from flask import Flask, render_template
import json

class Image:
    def __init__(self):
        a = 0
        
    def render(self, config):
        urls = json.loads(config)
        return render_template("Image.html", urls = urls)
