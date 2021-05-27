package com.example.prostoyspisok.database;

public class UserDBSchema{ //класс для описания структуры БД
    public static class UserTable{ //класс для таблицы users
        public static final String NAME = "users"; //Название таблицы БД

        public static final class Cols{ //класс для колонок
            public static final String UUID = "uuid";
            public static final String FIRSTNAME = "firstname";
            public static final String LASTNAME = "lastname";
            public static final String PHONE = "phone";
        }
    }
}
