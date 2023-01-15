/**
 *    Copyright 2009-2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.reflection;

import org.apache.ibatis.domain.misc.RichType;
import org.apache.ibatis.domain.misc.generics.GenericConcrete;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class MetaClassTest2 {

    private class Author {
      private Integer id;
      private String name;
      private Integer age;
      /** 一个作者对应多篇文章 */
      private Article[] articles;

      // 省略 getter/setter
    }

    private class Article {
      private Integer id;
      private String title;
      private String content;
      /** 一篇文章对应一个作者 */
      private Author author;

      // 省略 getter/setter
    }

    @Test
    public void testHasSetter() {
      // 为 Author 创建元信息对象
      MetaClass authorMeta = MetaClass.forClass(Author.class, new DefaultReflectorFactory());
      System.out.println("------------☆ Author ☆------------");
      System.out.println("id -> " + authorMeta.hasSetter("id"));
      System.out.println("name -> " + authorMeta.hasSetter("name"));
      System.out.println("age -> " + authorMeta.hasSetter("age"));
      // 检测 Author 中是否包含 Article[] 的 setter
      System.out.println("articles -> " + authorMeta.hasSetter("articles"));
      System.out.println("articles[] -> " + authorMeta.hasSetter("articles[]"));
      System.out.println("title -> " + authorMeta.hasSetter("title"));

      // 为 Article 创建元信息对象
      MetaClass articleMeta = MetaClass.forClass(Article.class, new DefaultReflectorFactory());
      System.out.println("\n------------☆ Article ☆------------");
      System.out.println("id -> " + articleMeta.hasSetter("id"));
      System.out.println("title -> " + articleMeta.hasSetter("title"));
      System.out.println("content -> " + articleMeta.hasSetter("content"));
      // 下面两个均为复杂属性，分别检测 Article 类中的 Author 类是否包含 id 和 name 的 setter 方法
      System.out.println("author.id -> " + articleMeta.hasSetter("author.id"));
      System.out.println("author.name -> " + articleMeta.hasSetter("author.name"));
    }
  }
