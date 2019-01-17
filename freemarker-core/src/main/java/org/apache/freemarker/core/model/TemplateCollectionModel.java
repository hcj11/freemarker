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

package org.apache.freemarker.core.model;

import org.apache.freemarker.core.TemplateException;

/**
 * "collection" template language data type: Extends {@link TemplateIterableModel} with the ability to get the
 * whether there's any element, and the ability to get number of elements in the collection.
 */
public interface TemplateCollectionModel extends TemplateIterableModel {

    TemplateCollectionModel EMPTY_COLLECTION = new EmptyCollectionModel();

    /**
     * Returns the number items in this collection, or {@link Integer#MAX_VALUE}, if there are more than
     * {@link Integer#MAX_VALUE} items.
     */
    int getCollectionSize() throws TemplateException;

    /**
     * Returns if the collection contains any elements. This differs from {@code getCollectionSize() != 0} only in that
     * the exact number of items need not be calculated.
     */
    boolean isEmptyCollection() throws TemplateException;

}