package com.example.hr.domain;

// Aggregate -> Entity Root
// Entity -> i. identity
// Effective Java -> Builder (Template)
@Aggregate
public class Employee {

	private final TcKimlikNo identity;
	private FullName fullname;
	private Iban iban;
	private Money money;
	private Department department;
	private BirthYear birthYear;
	private Photo photo;
	private JobType jobType;

	public Employee(TcKimlikNo identity) {
		this.identity = identity;
	}

	public Employee(TcKimlikNo identity, FullName fullname) {
		this(identity);
		this.fullname = fullname;
	}

	public Employee(Builder builder) {
		this.identity = builder.identity;
		this.fullname = builder.fullname;
		this.iban = builder.iban;
		this.money = builder.money;
		this.birthYear = builder.birthYear;
		this.photo = builder.photo;
		this.jobType = builder.jobType;
		this.department = builder.department;
	}

	public TcKimlikNo getIdentity() {
		return this.identity;
	}

	public FullName getFullname() {
		return fullname;
	}

	public void setFullname(FullName fullname) {
		this.fullname = fullname;
	}

	public Money getMoney() {
		return money;
	}

	public void setMoney(Money money) {
		this.money = money;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public BirthYear getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(BirthYear birthYear) {
		this.birthYear = birthYear;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public JobType getJobType() {
		return jobType;
	}

	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}

	public void setIban(Iban iban) {
		this.iban = iban;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identity == null) ? 0 : identity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (identity == null) {
			if (other.identity != null)
				return false;
		} else if (!identity.equals(other.identity))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [identity=" + identity + ", fullname=" + fullname + ", iban=" + iban + ", money=" + money
				+ ", department=" + department + ", birthYear=" + birthYear + ", jobType=" + jobType + "]";
	}

	public static class Builder {
		private TcKimlikNo identity;
		private FullName fullname;
		private Iban iban;
		public Money money;
		private Department department;
		private BirthYear birthYear;
		private Photo photo;
		private JobType jobType;

		public Builder(TcKimlikNo identity) {
			this.identity = identity;
		}

		public Builder fullname(String firstName, String lastName) {
			this.fullname = FullName.valueOf(firstName, lastName);
			return this;
		}

		public Builder iban(String value) {
			this.iban = Iban.of(value);
			return this;
		}

		public Builder salary(int i, FiatCurrency tl) {
			return this;
		}

		public Builder birthYear(int value) {
			this.birthYear = BirthYear.valueOf(value);
			return this;
		}

		public Builder photo(byte[] data) {
			this.photo = Photo.of(data);
			return this;
		}

		public Builder jobType(String value) {
			this.jobType = JobType.valueOf(value);
			return this;
		}

		public Builder department(String value) {
			this.department = Department.valueOf(value);
			return this;
		}

		public Employee build() {
			// Validation
			// Business Rule
			// Invariant Test
			// Caching, ...
			return new Employee(this);
		}

		public Builder salary(double value, FiatCurrency currency) {
			this.money = Money.of(value, currency);
			return this;
		}

	}

	public Iban getIban() {
		return this.iban;
	}

	public Money getSalary() {
		return this.money;
	}

}
