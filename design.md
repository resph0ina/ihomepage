# Project Design
## 功能分解

1. 一个页面，可以交互含动画效果
2. 页面交互可以编辑模块 - 包含模块的信息编辑与增删
3. 服务器端账户系统。储存模块信息和登录信息等
4. \*用户自定义模块 - 要求界面以及服务器端的service动态加载

## 结构
### 1. 服务器

* flask框架
* 用户网页
	* 账户系统
	* 数据库结构设计：账户以及个人模块信息
* 处理流程

		1. 用户打开网页
		2. 服务器返回页面与模块信息
		3. 每个模块分别请求内容
			1. 模块请求服务器
			2. 服务器请求service
			3. 返回之后请求renderer
			4. 得到HTML返回用户
		（         服务器                  service             renderer      ）
		（	请求json ---> 带个人设置的json  --->  返回的数据json  --->  返回HTML ）

* 模块接口
	* web service接口

			通过python爬虫，提供数据
			请求：/service/service_name/
				POST数据：json {action:%s, width:%d, height:%d}
			返回：json {type:%s（指定renderer）, data:{[每一项的数据，格式依据renderer要求]}}
			
			textlines : [{name:string, text:string}]
			pictextlines : [{name:string, pic:urlstring, text:string}]
			image : [pic:urlstring]
			raw : content:string
			


	* renderer接口

			不同类型的渲染器，接收数据生成HTML
			比如：每项有一个头像，一行字的json，渲染成类似人人的效果
			     每项只有一行字的（新闻等），渲染成间隔行颜色不同的较舒服的效果
### 2. 独立模块
* 许多独立的web service，我们实现以下内容：

		* 公共信息：
			1. 天气
			2. 新闻头条
			3. 搜索（就显示一个文本框）
		* 个人信息：
			1. 人人
			2. 网络学堂

* \*扩展模块 - 估计没时间做了

### 3. 用户网页

* 充分利用现有前端框架以及动画效果的js库
* 就两个页面
	1. 呈现页
	注意配色，可以固定配色
	
	2. 设置页
	* 可以拖拽挪动位置
	* 可以通过按钮调整尺寸
	* 增删项目等
	
## 分工

主要工作大概有：

1. 后端流程代码
2. 5个默认模块
3. 前端网页（工作量不小）

----
git9 的markdown看起来不正常。。github好一点
