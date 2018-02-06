package org.myclients.domain;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.jeneva.Dto;
import org.jeneva.Dtobase;
import org.jeneva.IChild;

@JsonSerialize(include=Inclusion.NON_NULL)
public class Login extends Dtobase implements IChild {

	private Integer id;
	private String name;
	private String password;
	private Boolean enabled;
	private Client client;

	@Dto(Levels.ID)
	public Integer getId()								{ return this.id; }
	public void setId(Integer value)					{ this.id = value; }

	@Dto(Levels.LOOKUP)
	public String getName()								{ return this.name; }
	public void setName(String value)					{ this.name = value; }

	@Dto(Levels.GRID)
	public String getPassword()							{ return this.password; }
	public void setPassword(String value)				{ this.password = value; }

	@Dto(Levels.GRID)
	public Boolean getEnabled()							{ return this.enabled; }
	public void setEnabled(Boolean value)				{ this.enabled = value; }

	@Dto(value = Levels.NEVER)
	public Client getClient()							{ return this.client; }
	public void setClient(Client value)					{ this.client = value; }

	@Override
	public boolean equals(Object obj) {
		return Comparator.areEqual(this.getId(), ((Login)obj).getId());
	}

	@Override
	public void connectToParent(Object parent) {
		if(parent instanceof Client) {
			this.client = (Client)parent;
		}
	}
}