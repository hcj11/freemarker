/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.freemarker.core;

import java.io.IOException;

import org.apache.freemarker.core.debug.impl.DebuggerService;

/**
 * Don't use this; used internally by FreeMarker, might changes without notice.
 * A debug breakpoint inserted into the template 
 */
class _ASTDebugBreak extends _ASTElement {
    public _ASTDebugBreak(_ASTElement nestedBlock) {
        addChild(nestedBlock);
        copyLocationFrom(nestedBlock);
    }
    
    @Override
    protected _ASTElement[] accept(Environment env) throws TemplateException, IOException {
        if (!DebuggerService.suspendEnvironment(
                env, getTemplate().getSourceName(), getChild(0).getBeginLine())) {
            return getChild(0).accept(env);
        } else {
            throw new StopException(env, "Stopped by debugger");
        }
    }

    @Override
    protected String dump(boolean canonical) {
        if (canonical) {
            StringBuilder sb = new StringBuilder();
            sb.append("<#-- ");
            sb.append("debug break");
            if (getChildCount() == 0) {
                sb.append(" /-->");
            } else {
                sb.append(" -->");
                sb.append(getChild(0).getCanonicalForm());                
                sb.append("<#--/ debug break -->");
            }
            return sb.toString();
        } else {
            return "debug break";
        }
    }
    
    @Override
    String getNodeTypeSymbol() {
        return "#debug_break";
    }

    @Override
    int getParameterCount() {
        return 0;
    }

    @Override
    Object getParameterValue(int idx) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    ParameterRole getParameterRole(int idx) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    boolean isNestedBlockRepeater() {
        return false;
    }
        
}
