# -*- coding:utf-8 -*-
# Filename:Renren.py
#
from HTMLParser import HTMLParser
from re import match
from urllib import urlencode
import os, re, json, sys
import threading, time
import urllib, urllib2, socket, cookielib
import shelve
from BeautifulSoup import BeautifulSoup


# 避免urllib2永远不返回
socket.setdefaulttimeout(10)

class RenrenRequester:
    '''
    人人访问器
    '''
    LoginUrl = 'http://www.renren.com/Login.do'

    def CreateByCookie(self, cookie):
        self.ckjar = cookielib.CookieJar()
        cookieFile = urllib2.HTTPCookieProcessor(self.ckjar)
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
        self.ckjar = cookielib.CookieJar()
        cookieFile = urllib2.HTTPCookieProcessor(self.ckjar)
        self.opener = urllib2.build_opener(cookieFile)
        self.opener.addheaders = [('User-agent', 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.4 (KHTML, like Gecko) Chrome/22.0.1229.92 Safari/537.4')]
        req = urllib2.Request(self.LoginUrl, postData)
        result = self.opener.open(req)

        if not self.__FindInfoWhenLogin(result):
            return False

        return True

    def __FindInfoWhenLogin(self, result):
        result_url = result.geturl()
        
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
                data['requestToken'] = self.requestToken
                data['_rtk'] = self._rtk
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

class RenrenGrabber:
    '''
    SuperRenren
        用户接口
    '''
    def __init__(self):
        self.__isLogin = False
        self.requester = RenrenRequester()

    # 创建
    def Create(self, username, password):
        if self.__isLogin : return
        if self.requester.Create(username, password):
            self.__GetInfoFromRequester()
            self.__isLogin= True
        else:
            self.__isLogin= False

    def CreateByCookie(self, cookie):
        if self.__isLogin : return
        if self.requester.CreateByCookie(cookie):
            self.__GetInfoFromRequester()
            self.__isLogin= True
        else:
            self.__isLogin= False

    def __GetInfoFromRequester(self):
        self.userid = self.requester.userid
        self.requestToken = self.requester.requestToken
        self._rtk = self.requester._rtk

    def GetMsg(self):
        html, url = self.requester.Request('http://www.renren.com/feedretrieve4.do',
            data = {'byTime':'0', 'begin':'0', 'limit':'10', 'isjson':'0', 'view':'10', 'type':'3'})
        soup=BeautifulSoup(html)
        items=soup.findAll('div',{'class':'a-feed'})
        data = list()
        for item in items:
            i = dict()
            name = item.a.img.get('title')
            i['image'] = item.a.img.get('src')
            jibun = item.find('p',{'class':'status'})
            if jibun == None:
                i['text'] = name + ': (zz)' + item.find('p',{'class':'share-status'}).text
            else:
                i['text'] = name + ': ' + jibun.text
            data.append(i)
        return data

    def grab(self, arg = {}):
        if self.__isLogin == False : self.Create(arg['username'],arg['password'])
        if self.__isLogin == False : return []
        return {
        'url':'http://www.renren.com','name':u'人人网 好友状态','type':'imgtextlines','data':self.GetMsg()}

    # 发送个人状态
    def PostMsg(self, msg):
        poster = RenrenPostMsg()
        poster.Handle(self.requester, (self.requestToken, self.userid, self._rtk, msg))


if __name__ == '__main__':
    r = SuperRenren()
    r.Create('18911029092', 'THUcst)(')
    html, url = r.requester.Request('http://www.renren.com/feedretrieve4.do',
        data = {'byTime':'0', 'begin':'0', 'limit':'5', 'isjson':'0', 'view':'5', 'type':'3'})
    open('main.txt','w').write(html)