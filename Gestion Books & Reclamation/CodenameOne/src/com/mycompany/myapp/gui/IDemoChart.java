/**
 * Copyright (C) 2009 - 2013 SC 4ViewSoft SRL
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Form;





/**
 * Defines the demo charts.
 */
public interface IDemoChart {
  /** A constant for the name field in a list activity. */
  String NAME = "name";
  /** A constant for the description field in a list activity. */
  String DESC = "desc";

  /**
   * Returns the chart name.
   * 
   * @return the chart name
   */
  String getName();

  /**
   * Returns the chart description.
   * 
   * @return the chart description
   */
  String getDesc();

  /**
   * Executes the chart demo.
   * 
   * @param context the context
   * @return the built intent
   */
  Form execute();

}
