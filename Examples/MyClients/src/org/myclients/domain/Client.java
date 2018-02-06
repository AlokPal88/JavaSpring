package org.myclients.domain;

import java.util.Set;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.jeneva.Dto;
import org.jeneva.Dtobase;

@JsonSerialize(include=Inclusion.NON_NULL)
public class Client extends Dtobase {

	private Integer id;
	private String name;
	private String lastname;
	private Integer age;
	private Set<Login> logins;

	@Dto(Levels.ID)
	public Integer getId()								{ return this.id; }
	public void setId(Integer value)					{ this.id = value; }

	@Dto(Levels.LOOKUP)
	public String getName()								{ return this.name; }
	public void setName(String value)					{ this.name = value; }

	@Dto(Levels.GRID)
	public String getLastname()							{ return this.lastname; }
	public void setLastname(String value)				{ this.lastname = value; }

	@Dto(Levels.GRID)
	public Integer getAge()								{ return this.age; }
	public void setAge(Integer value)					{ this.age = value; }

	@Dto(value = Levels.GRID, nested = Levels.LOOKUP, dynamic = true)
	public Set<Login> getLogins()						{ return this.logins; }
	public void setLogins(Set<Login> value)				{ this.logins = value; }

	@Override
	public boolean equals(Object obj) {
		return Comparator.areEqual(this.getId(), ((Client)obj).getId());
	}
}