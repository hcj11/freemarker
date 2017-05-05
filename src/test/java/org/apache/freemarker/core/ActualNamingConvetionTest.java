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

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.freemarker.test.TestConfigurationBuilder;
import org.junit.Test;

public class ActualNamingConvetionTest {
    
    @Test
    public void testUndetectable() throws IOException {
        final String ftl = "<#if true>${x?size}</#if>";
        assertEquals(getActualNamingConvention(ftl,
                ParsingConfiguration.AUTO_DETECT_NAMING_CONVENTION), ParsingConfiguration.AUTO_DETECT_NAMING_CONVENTION);
        assertEquals(getActualNamingConvention(ftl,
                ParsingConfiguration.LEGACY_NAMING_CONVENTION), ParsingConfiguration.LEGACY_NAMING_CONVENTION);
        assertEquals(getActualNamingConvention(ftl,
                ParsingConfiguration.CAMEL_CASE_NAMING_CONVENTION), ParsingConfiguration.CAMEL_CASE_NAMING_CONVENTION);
    }

    @Test
    public void testLegacyDetected() throws IOException {
        final String ftl = "${x?upper_case}";
        assertEquals(getActualNamingConvention(ftl,
                ParsingConfiguration.AUTO_DETECT_NAMING_CONVENTION), ParsingConfiguration.LEGACY_NAMING_CONVENTION);
        assertEquals(getActualNamingConvention(ftl,
                ParsingConfiguration.LEGACY_NAMING_CONVENTION), ParsingConfiguration.LEGACY_NAMING_CONVENTION);
    }

    @Test
    public void testCamelCaseDetected() throws IOException {
        final String ftl = "${x?upperCase}";
        assertEquals(getActualNamingConvention(ftl,
                ParsingConfiguration.AUTO_DETECT_NAMING_CONVENTION), ParsingConfiguration.CAMEL_CASE_NAMING_CONVENTION);
        assertEquals(getActualNamingConvention(ftl,
                ParsingConfiguration.CAMEL_CASE_NAMING_CONVENTION), ParsingConfiguration.CAMEL_CASE_NAMING_CONVENTION);
    }

    private int getActualNamingConvention(String ftl, int namingConvention) throws IOException {
        return new Template(null, ftl,
                new TestConfigurationBuilder().namingConvention(namingConvention).build())
                .getActualNamingConvention();
    }
    
}
