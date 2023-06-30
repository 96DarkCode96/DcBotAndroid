package me.darkcode.dcbotandroid.object.permission;

public enum PermissionOverrideType {
	ROLE(0),MEMBER(1);

	private final int id;

	PermissionOverrideType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static PermissionOverrideType get(int id){
		switch (id) {
			case 0:
				return ROLE;
			case 1:
				return MEMBER;
			default:
				throw new IllegalArgumentException("Invalid permission type for id: " + id);
		}
	}

}