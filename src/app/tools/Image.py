from flask import Flask, render_template

class Image:
    def render(config):
        return render_template("Image.html", config = config)
