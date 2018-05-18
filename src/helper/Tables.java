package helper;
import java.util.ArrayList;

public class Tables {
	String name;
	ArrayList<String>attributes;
	ArrayList<String> types;
	ArrayList<String> sizes;

	public ArrayList<String> getSizes() {
		return sizes;
	}
	public void setSizes(ArrayList<String> sizes) {
		this.sizes = sizes;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getAttributes() {
		return attributes;
	}
	public void setAttributes(ArrayList<String> attributes) {
		this.attributes = attributes;
	}
	public ArrayList<String> getTypes() {
		return types;
	}
	public void setTypes(ArrayList<String> types) {
		this.types = types;
	}
	
}
