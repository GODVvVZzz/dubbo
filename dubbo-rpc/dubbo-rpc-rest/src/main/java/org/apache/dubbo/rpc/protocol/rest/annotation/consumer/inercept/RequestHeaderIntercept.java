/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.rpc.protocol.rest.annotation.consumer.inercept;


import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.metadata.rest.RestMethodMetadata;
import org.apache.dubbo.remoting.http.RequestTemplate;
import org.apache.dubbo.rpc.protocol.rest.annotation.consumer.HttpConnectionCreateContext;
import org.apache.dubbo.rpc.protocol.rest.annotation.consumer.HttpConnectionPreBuildIntercept;
import org.apache.dubbo.rpc.protocol.rest.constans.RestConstant;

import java.util.Collection;
import java.util.Set;

@Activate(value = RestConstant.REQUEST_HEADER_INTERCEPT,order = 2)
public class RequestHeaderIntercept implements HttpConnectionPreBuildIntercept {

    @Override
    public void intercept(HttpConnectionCreateContext connectionCreateContext) {

        RestMethodMetadata restMethodMetadata = connectionCreateContext.getRestMethodMetadata();
        RequestTemplate requestTemplate = connectionCreateContext.getRequestTemplate();



        Set<String> consumes = restMethodMetadata.getRequest().getConsumes();

        requestTemplate.addHeaders(RestConstant.CONTENT_TYPE, consumes);

        Collection<String> headers = requestTemplate.getHeaders(RestConstant.ACCEPT);
        if (headers == null || headers.isEmpty()) {
            requestTemplate.addHeader(RestConstant.ACCEPT, RestConstant.DEFAULT_ACCEPT);
        }


    }


}
