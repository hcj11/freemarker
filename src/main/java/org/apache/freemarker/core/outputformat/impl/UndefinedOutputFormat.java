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
package org.apache.freemarker.core.outputformat.impl;

import org.apache.freemarker.core.Configuration;
import org.apache.freemarker.core.model.TemplateMarkupOutputModel;
import org.apache.freemarker.core.outputformat.OutputFormat;

/**
 * Represents the output format used when the template output format is undecided. This is the default output format if
 * FreeMarker can't select anything more specific (see {@link Configuration#getTemplateConfigurations()}). This format
 * doesn't support auto-escaping ({@link Configuration#getAutoEscapingPolicy()}). It will print
 * {@link TemplateMarkupOutputModel}-s as is (doesn't try to convert them).
 * 
 * @see PlainTextOutputFormat
 * 
 * @since 2.3.24
 */
public final class UndefinedOutputFormat extends OutputFormat {

    public static final UndefinedOutputFormat INSTANCE = new UndefinedOutputFormat();
    
    private UndefinedOutputFormat() {
        // Only to decrease visibility
    }

    @Override
    public boolean isOutputFormatMixingAllowed() {
        return true;
    }

    @Override
    public String getName() {
        return "undefined";
    }

    @Override
    public String getMimeType() {
        return null;
    }

}
