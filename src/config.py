# -*- coding:utf-8 -*-
from app.modules import *
from app.tools import *
import os
basedir = os.path.abspath(os.path.dirname(__file__))

CSRF_ENABLED = True
SECRET_KEY = 'you-will-never-guess'
    
SQLALCHEMY_DATABASE_URI = 'sqlite:///' + os.path.join(basedir, 'app.db')
SQLALCHEMY_MIGRATE_REPO = os.path.join(basedir, 'db_repository')

SERVICES = {'baidu.news': TestGrabber.TestGrabber(), 
	'renren.status': RenrenGrabber.RenrenGrabber(),
	'weather.simple': WeatherGrabber.WeatherGrabber(),
	'baidu.hotword' : Baiduword.BaiduWordGrabber(),
	'baidu.worldcup' : BaiduWcup.BaiduWorldcupGrabber(),
	'douban.recent' : Douban.Douban()}
TOOLS = {'image': Image.Image()}
BLOCKNAME = {u'百度热搜词':'baidu.hotword',
	u'人人新鲜事':'renren.status',
	u'实时天气情况':'weather.simple',
	u'世界杯今日明星':'baidu.worldcup',
	u'自定义服务':'custom',
	u'豆瓣最近收听':'douban.recent',
	u'自定义图片':'image'}