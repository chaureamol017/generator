package com.mycomp.generator.curd;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.mycomp.generator.curd.model.ClassDetails;

public class CurdGeneratorMain {

	public static void main(String[] args) {
		try {
			File file = new File(
					"G:\\WorkSpace\\WebApps\\bank-management\\src\\main\\java\\com\\mycomp\\fin\\bank\\user");
			file.mkdir();

			String data = getData();
			final FileWriter writer = new FileWriter(
					"G:\\WorkSpace\\WebApps\\bank-management\\src\\main\\java\\com\\mycomp\\fin\\bank\\user\\User.java");
			writer.write(data);
			writer.write(data);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static String getData() {
		return "package com.mycomp.business.krishi.entity;\r\n" + "\r\n" + "import java.io.Serializable;\r\n"
				+ "import java.sql.Date;\r\n" + "\r\n" + "import javax.persistence.Column;\r\n"
				+ "import javax.persistence.Entity;\r\n" + "import javax.persistence.EnumType;\r\n"
				+ "import javax.persistence.Enumerated;\r\n" + "import javax.persistence.GeneratedValue;\r\n"
				+ "import javax.persistence.GenerationType;\r\n" + "import javax.persistence.Id;\r\n"
				+ "import javax.persistence.Table;\r\n" + "\r\n"
				+ "import com.mycomp.business.krishi.entity.type.Gender;\r\n"
				+ "import com.mycomp.business.krishi.entity.type.MaritalStatus;\r\n"
				+ "import com.mycomp.business.krishi.entity.type.UserRole;\r\n" + "\r\n" + "/**\r\n" + " *\r\n"
				+ " * @author Amol\r\n" + " */\r\n" + "\r\n" + "@Entity\r\n" + "@Table(name = \"users\")\r\n"
				+ "public class User implements Serializable {\r\n" + "    /**\r\n" + "	 * \r\n" + "	 */\r\n"
				+ "	private static final long serialVersionUID = 1L;\r\n" + "	@Id\r\n"
				+ "    @Column(name = \"user_id\")\r\n" + "	@GeneratedValue(strategy = GenerationType.IDENTITY)\r\n"
				+ "    private Long userId;\r\n" + "	\r\n" + "    @Column(name = \"first_name\")\r\n"
				+ "    private String firstName;\r\n" + "    \r\n" + "    @Column(name = \"middle_name\")\r\n"
				+ "    private String middleName;\r\n" + "    \r\n" + "    @Column(name = \"last_name\")\r\n"
				+ "    private String lastName;\r\n" + "    \r\n" + "    @Column(name = \"emailid\")\r\n"
				+ "    private String emailId;\r\n" + "    \r\n" + "    @Column(name = \"mobile\")\r\n"
				+ "    private String mobile;\r\n" + "    \r\n" + "    @Column(name = \"role\")\r\n"
				+ "    @Enumerated(EnumType.STRING)\r\n" + "    private UserRole role;\r\n" + "\r\n"
				+ "    @Column(name = \"gender\")\r\n" + "    @Enumerated(EnumType.STRING)\r\n"
				+ "    private Gender gender;\r\n" + "\r\n" + "    @Column(name = \"marital_status\")\r\n"
				+ "    @Enumerated(EnumType.STRING)\r\n" + "    private MaritalStatus maritalStatus;\r\n" + "    \r\n"
				+ "    @Column(name = \"birth_date\")\r\n" + "    private Date birthDate;\r\n" + "\r\n"
				+ "	public Long getUserId() {\r\n" + "		return userId;\r\n" + "	}\r\n" + "\r\n"
				+ "	public void setUserId(Long userId) {\r\n" + "		this.userId = userId;\r\n" + "	}\r\n" + "\r\n"
				+ "	public String getFirstName() {\r\n" + "		return firstName;\r\n" + "	}\r\n" + "\r\n"
				+ "	public void setFirstName(String firstName) {\r\n" + "		this.firstName = firstName;\r\n"
				+ "	}\r\n" + "\r\n" + "	public String getMiddleName() {\r\n" + "		return middleName;\r\n"
				+ "	}\r\n" + "\r\n" + "	public void setMiddleName(String middleName) {\r\n"
				+ "		this.middleName = middleName;\r\n" + "	}\r\n" + "\r\n" + "	public String getLastName() {\r\n"
				+ "		return lastName;\r\n" + "	}\r\n" + "\r\n" + "	public void setLastName(String lastName) {\r\n"
				+ "		this.lastName = lastName;\r\n" + "	}\r\n" + "\r\n" + "	public String getEmailId() {\r\n"
				+ "		return emailId;\r\n" + "	}\r\n" + "\r\n" + "	public void setEmailId(String emailId) {\r\n"
				+ "		this.emailId = emailId;\r\n" + "	}\r\n" + "\r\n" + "	public String getMobile() {\r\n"
				+ "		return mobile;\r\n" + "	}\r\n" + "\r\n" + "	public void setMobile(String mobile) {\r\n"
				+ "		this.mobile = mobile;\r\n" + "	}\r\n" + "\r\n" + "	public UserRole getRole() {\r\n"
				+ "		return role;\r\n" + "	}\r\n" + "\r\n" + "	public void setRole(UserRole role) {\r\n"
				+ "		this.role = role;\r\n" + "	}\r\n" + "\r\n" + "	public Gender getGender() {\r\n"
				+ "		return gender;\r\n" + "	}\r\n" + "\r\n" + "	public void setGender(Gender gender) {\r\n"
				+ "		this.gender = gender;\r\n" + "	}\r\n" + "\r\n"
				+ "	public MaritalStatus getMaritalStatus() {\r\n" + "		return maritalStatus;\r\n" + "	}\r\n"
				+ "\r\n" + "	public void setMaritalStatus(MaritalStatus maritalStatus) {\r\n"
				+ "		this.maritalStatus = maritalStatus;\r\n" + "	}\r\n" + "\r\n"
				+ "	public Date getBirthDate() {\r\n" + "		return birthDate;\r\n" + "	}\r\n" + "\r\n"
				+ "	public void setBirthDate(Date birthDate) {\r\n" + "		this.birthDate = birthDate;\r\n"
				+ "	}\r\n" + "}\r\n" + "";
	}

}
