from flask import Flask, render_template
import json

class Index:
    def __init(self):
        a = 0

    def render(self, config):
        index = json.loads(config)
        return render_template("Index.html", index = index)
