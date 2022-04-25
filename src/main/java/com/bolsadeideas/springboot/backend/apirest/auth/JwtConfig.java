package com.bolsadeideas.springboot.backend.apirest.auth;

public class JwtConfig {
	
	public static final String LLAVE_SECRETA ="alguna.clave.secreta.12345645";
	
	public static final String RSA_PRIVATE="-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEogIBAAKCAQEAwAwHC2YnTeq2j7n9hK7qcLOsL8AarTOcUZrocmk9jRLhahn8\r\n"
			+ "YQ62VeTVQH6g/ZbROewTwXLMwXpAeJn5BCFbDgft3znkJIa3OYTFDRwRIEdOeOZ6\r\n"
			+ "ix5mRELbsO5hrqqUEbr0BrRL4rwfCWVJRnji3pnPrg9YI6rHzgTxZXTk8zJnX4T2\r\n"
			+ "hgKDn09pUdUWxP1cvMkrDlp0kDT28FZTYuu1kaIkCZodlyD0hifBZAjeza4QkYfU\r\n"
			+ "X6JPqYAagl7Z4BMmd4rEgobopleZB6WWB9F2QxYGiekXj4jrSV+C73OkvrXu9z55\r\n"
			+ "TZbVvnxLFRYyDLL2sGwymgC+P1HZQhyCnHoe5wIDAQABAoIBAGgr3gJb5gQIxgBO\r\n"
			+ "VesL6lU50LOSE6ZRsYA+c8njtiR40lb4Q4LuNQ+AcgBq766+iT1PBiRPvitQwfnp\r\n"
			+ "Iz8PTNHekTQdtdfuTyLfFvgWel+07ib8dgpScgyi64IcvFW/Z1P6wfWFBQi3HGq/\r\n"
			+ "SzDzz7QJYLK81gV61Ki226456xrTEosuPQepEzqUnG/NLigMoqELWRw2WvXHVeMN\r\n"
			+ "ZR5J30Nzm+4iFlGoZPKXIahjBtKT833riRV5BCL0FpSIxg+rJFk/JeGhiBu+NIlC\r\n"
			+ "7NMVV0hNmCeyKCvSP5zxPxHZ52YnZwDNOJoEtn5XYKLYJX471IUE3L0zDO2SecpN\r\n"
			+ "eZCmLJkCgYEA6X29iFiLIosbEXSyxaWYz4wYzH3DoDA4/p6AW7H+0u290WqT3zhk\r\n"
			+ "i0VAgl8CHuMFE3as+o7R0dIFc2Q365FYJJ+Q2B+vEKMl+1LB2Kkf7uKilBbM93xZ\r\n"
			+ "sdLas/AGcP2EJlg30FYZDajLS61LxcxP36FCD7QkBBFVXABNdTy44t0CgYEA0o9/\r\n"
			+ "i5L7kU0uud7zmFA0cu+7rnspcK+dShLUUTS1ruOeCs+4OngB69P/PxSpqBcdk+Mh\r\n"
			+ "fRdhJ5d1KzM2ofhkbsZ85Jw6QHc71wTDbxdmLb6Q5a7mdkK10GxLPbesOBxt9PN7\r\n"
			+ "SFoeUwUBwOdMio1NWAYEIvve+SEQbzHIr+3AopMCgYBth32ccyS5jJWjB6JBEyBu\r\n"
			+ "4+JSMwDMqTJjRskhuoq2Oa4cialaQiLwLglH3veos4gTDE2yr8J/ccz6x11wFNtL\r\n"
			+ "OssAh0RhQpczoSurzhJbxlbLrzJxOhT9VNMhK4xp/pK6RhHQmkLgZHkRQ/bSj7g8\r\n"
			+ "SHIr2a43dkYbhUzQnS6BxQKBgHjMyPDrd5aKAFe0uG5W/XA/Z56XnFPA/O1FVjfs\r\n"
			+ "lJtsjPxI5bntpbSFoLexBBCiil6Q/WKF6kBLnXYWYK6WEhCXoYmKs8TOYy9d1T3D\r\n"
			+ "3+Q4o2qSaDfK2AJmTthuqshaTx8sHTuKMEGS6smgKYKHxKrOcMsdU3P8azBQEIIW\r\n"
			+ "VeEvAoGAVtXQV7IFyc7ND2Q57icKK3d/EHkhFYS5s1gEawn8ZbNJApKmkzQ+YdJv\r\n"
			+ "2N1MwEbinPoNBTsbmWUq7CA+7h9lGEFw/R91HYOjXGwAEzKSg6Y5jkVpJfw7A85G\r\n"
			+ "ESyYCeyBVU5cqOJtvmo7dPZGzAIf76DdH8oMosrFMS76KGfo+HU=\r\n"
			+ "-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwAwHC2YnTeq2j7n9hK7q\r\n"
			+ "cLOsL8AarTOcUZrocmk9jRLhahn8YQ62VeTVQH6g/ZbROewTwXLMwXpAeJn5BCFb\r\n"
			+ "Dgft3znkJIa3OYTFDRwRIEdOeOZ6ix5mRELbsO5hrqqUEbr0BrRL4rwfCWVJRnji\r\n"
			+ "3pnPrg9YI6rHzgTxZXTk8zJnX4T2hgKDn09pUdUWxP1cvMkrDlp0kDT28FZTYuu1\r\n"
			+ "kaIkCZodlyD0hifBZAjeza4QkYfUX6JPqYAagl7Z4BMmd4rEgobopleZB6WWB9F2\r\n"
			+ "QxYGiekXj4jrSV+C73OkvrXu9z55TZbVvnxLFRYyDLL2sGwymgC+P1HZQhyCnHoe\r\n"
			+ "5wIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";
}
