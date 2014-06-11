# -*- coding:utf-8 -*-
# Filename:Renren.py
#
from HTMLParser import HTMLParser
from re import match
from urllib import urlencode
import os, re, json, sys
import threading, time
import urllib, urllib2, socket
import shelve


# 避免urllib2永远不返回
socket.setdefaulttimeout(20)

class RenrenRequester:
    '''
    人人访问器
    '''
    LoginUrl = 'http://www.renren.com/Login.do'

    def CreateByCookie(self, cookie):
        cookieFile = urllib2.HTTPCookieProcessor()
        self.opener = urllib2.build_opener(cookieFile)
        self.opener.addheaders = [('User-agent', 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.4 (KHTML, like Gecko) Chrome/22.0.1229.92 Safari/537.4'),
                                  ('Cookie', cookie),
                                  ]
        
        req = urllib2.Request(self.LoginUrl)

        try:
            result = self.opener.open(req)
        except:
            return False

        if not self.__FindInfoWhenLogin(result):
            return False

        return True

    
    # 输入用户和密码的元组
    def Create(self, username, password):
        self.username = username
        self.password = password
        loginData = {'email':username,
                'password':password,
                'origURL':'http://www.renren.com',
                'formName':'',
                'method':'',
                'isplogin':'true',
                'submit':'登录'}
        postData = urlencode(loginData)
        cookieFile = urllib2.HTTPCookieProcessor()
        self.opener = urllib2.build_opener(cookieFile)
        self.opener.addheaders = [('User-agent', 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.4 (KHTML, like Gecko) Chrome/22.0.1229.92 Safari/537.4')]
        req = urllib2.Request(self.LoginUrl, postData)
        result = self.opener.open(req)

        if not self.__FindInfoWhenLogin(result):
            return False

        return True

    def __FindInfoWhenLogin(self, result):
        result_url = result.geturl()
        logger.info(result_url)
        
        rawHtml = result.read()
        self.mainHtml = rawHtml
        # print(rawHtml)

        # 获取用户id
        useridPattern = re.compile(r'\'id\':\'(\d+?)\'')
        try:
            self.userid = useridPattern.search(rawHtml).group(1)
        except:
            # GOD DAMN V7!
            v7Pattern = re.compile(r'id : \"(\d+?)\"')
            try:
                self.userid = v7Pattern.search(rawHtml).group(1)
                v7TokenPattern = re.compile(r'requestToken : \'(.+?)\'')
                v7RtkPattern = re.compile(r'_rtk : \'(.*?)\'')
                self.uiVersion = 'v7'
                self.requestToken = v7TokenPattern.findall(rawHtml)[0]
                self._rtk = v7RtkPattern.findall(rawHtml)[0]
                print ("7userid: %s, token: %s, rtk: %s" % (self.userid, self.requestToken, self._rtk))
                self.__isLogin = True      
                return self.__isLogin
            except Exception, e:
                print('Failed...')
                return False
        # 查找requestToken
        self.uiVersion = 'v6'
        pos = rawHtml.find("get_check:'")
        if pos == -1: return False        
        rawHtml = rawHtml[pos + 11:]
        token = match('-\d+', rawHtml)
        if token is None:
            token = match('\d+', rawHtml)
            if token is None: return False
        self.requestToken = token.group()  

        # 查找_rtk
        pos = rawHtml.find("get_check_x:'")
        if pos == -1: return False        
        self._rtk = rawHtml[pos + 13:pos + 13 +8]

        print("userid: %s, token: %s, rtk: %s" % (self.userid, self.requestToken, self._rtk))
        
        self.__isLogin = True      
        return self.__isLogin
    
    def GetRequestToken(self):
        return self.requestToken
    
    def GetUserId(self):
        return self.userid
    
    def Request(self, url, data = None):
        if self.__isLogin:
            if data:
                encodeData = urlencode(data)
                request = urllib2.Request(url, encodeData)
            else:
                request = urllib2.Request(url)

            count = 0
            while True:
                try:
                    count += 1
                    if count > 5:
                        break
                    result = self.opener.open(request)
                    url = result.geturl()
                    rawHtml = result.read()
                    break
                except (socket.timeout, urllib2.URLError):
                    print("Request Timeout")
                    continue
            return rawHtml, url
        else:
            return None
        
        
class RenrenPostMsg:
    '''
    RenrenPostMsg
        发布人人状态
        '''
    
    def Handle(self, requester, param):
        requestToken, userid, _rtk, msg = param
        newStatusUrl = 'http://shell.renren.com/' + str(userid) + '/status'
        print newStatusUrl
        statusData = {'content':msg,
                      'hostid':userid,
                    'requestToken':requestToken,
                      '_rtk':_rtk,
                      'channel':'renren'}
        postStatusData = urlencode(statusData)
        requester.Request(newStatusUrl, statusData)
        return True

class SuperRenren:
    '''
    SuperRenren
        用户接口
    '''
    # 创建
    def Create(self, username, password):
        self.requester = RenrenRequester()
        if self.requester.Create(username, password):
            self.__GetInfoFromRequester()
            return True
        return False

    def CreateByCookie(self, cookie):
        self.requester = RenrenRequester()
        if self.requester.CreateByCookie(cookie):
            self.__GetInfoFromRequester()
            return True
        return False

    def __GetInfoFromRequester(self):
        self.userid = self.requester.userid
        self.requestToken = self.requester.requestToken
        self._rtk = self.requester._rtk

	def GetMsg(self):
		html = self.requester.mainHtml
		if self.requester.uiVersion == 'v7':
			
    # 发送个人状态
    def PostMsg(self, msg):
        poster = RenrenPostMsg()
        poster.Handle(self.requester, (self.requestToken, self.userid, self._rtk, msg))
