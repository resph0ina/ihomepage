/*
 * Copyright 2008 Web Cohesion
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.renren.api.signature;


/**
 * 不支持的签名方法的异常
 * 
 * @author Ryan Heaton
 */
public class UnsupportedSignatureMethodException extends Exception {

    private static final long serialVersionUID = 1108134192433376266L;

    public UnsupportedSignatureMethodException(String msg) {
        super(msg);
    }
}
