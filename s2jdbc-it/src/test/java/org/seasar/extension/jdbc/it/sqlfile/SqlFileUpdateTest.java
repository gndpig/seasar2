/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.extension.jdbc.it.sqlfile;

import javax.persistence.EntityExistsException;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.it.entity.Department;
import org.seasar.extension.unit.S2TestCase;

/**
 * @author taedium
 * 
 */
public class SqlFileUpdateTest extends S2TestCase {

    private JdbcManager jdbcManager;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        include("jdbc.dicon");
    }

    /**
     * 
     * @throws Exception
     */
    public void testParameter_noneTx() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_no.sql";
        int result = jdbcManager.updateBySqlFile(path, null).execute();
        assertEquals(1, result);
        Department department =
            jdbcManager
                .selectBySql(
                    Department.class,
                    "select * from Department where department_id = 1")
                .getSingleResult();
        assertEquals(1, department.departmentId);
        assertEquals(10, department.departmentNo);
        assertEquals("ACCOUNTING", department.departmentName);
        assertEquals("hoge", department.location);
        assertEquals(1, department.version);
    }

    /**
     * 
     * @throws Exception
     */
    public void testParamter_simpleTypeTx() throws Exception {
        String path =
            getClass().getName().replace(".", "/") + "_simpleType.sql";
        int result = jdbcManager.updateBySqlFile(path, 2).execute();
        assertEquals(1, result);
        Department department =
            jdbcManager
                .selectBySql(
                    Department.class,
                    "select * from Department where department_id = 2")
                .getSingleResult();
        assertEquals(2, department.departmentId);
        assertEquals(20, department.departmentNo);
        assertEquals("RESEARCH", department.departmentName);
        assertEquals("hoge", department.location);
        assertEquals(1, department.version);
    }

    /**
     * 
     * @throws Exception
     */
    public void testParamter_dtoTx() throws Exception {
        String path = getClass().getName().replace(".", "/") + "_dto.sql";
        MyDto myDto = new MyDto();
        myDto.departmentId = 2;
        myDto.location = "foo";
        int result = jdbcManager.updateBySqlFile(path, myDto).execute();
        assertEquals(1, result);
        Department department =
            jdbcManager
                .selectBySql(
                    Department.class,
                    "select * from Department where department_id = 2")
                .getSingleResult();
        assertEquals(2, department.departmentId);
        assertEquals(20, department.departmentNo);
        assertEquals("RESEARCH", department.departmentName);
        assertEquals("foo", department.location);
        assertEquals(1, department.version);
    }

    /**
     * 
     * @throws Exception
     */
    public void testEntityExistsException_insertTx() throws Exception {
        String path =
            getClass().getName().replace(".", "/")
                + "_EntityExistsException_insert.sql";
        MyDto2 dto = new MyDto2();
        dto.departmentId = 1;
        dto.departmentNo = 50;
        try {
            jdbcManager.updateBySqlFile(path, dto).execute();
            fail();
        } catch (EntityExistsException e) {
        }
    }

    /**
     * 
     * @throws Exception
     */
    public void testEntityExistsException_updateTx() throws Exception {
        String path =
            getClass().getName().replace(".", "/")
                + "_EntityExistsException_update.sql";
        MyDto3 dto = new MyDto3();
        dto.departmentId = 1;
        dto.departmentId2 = 2;
        try {
            jdbcManager.updateBySqlFile(path, dto).execute();
            fail();
        } catch (EntityExistsException e) {
        }
    }

    /**
     * 
     * @author taedium
     * 
     */
    public static class MyDto {

        /** */
        public int departmentId;

        /** */
        public String location;
    }

    /**
     * 
     * @author taedium
     * 
     */
    public static class MyDto2 {

        /** */
        public int departmentId;

        /** */
        public int departmentNo;
    }

    /**
     * 
     * @author taedium
     * 
     */
    public static class MyDto3 {

        /** */
        public int departmentId;

        /** */
        public int departmentId2;
    }
}
