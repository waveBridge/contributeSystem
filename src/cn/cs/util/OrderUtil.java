package cn.cs.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import cn.cs.entity.Material;

public class OrderUtil {
	public static List<Material> sort(Set<Material> materialSet){
		System.out.println("sort...Set...");
		List<Material> materialList = new ArrayList<Material>();
		
		for(Material m : materialSet){
			materialList.add(m);
		}
		
		Collections.sort(materialList,  new SortByHotDegree());
		
		return materialList;
	}

	public static List<Material> sort(List<Material> materials) {
		System.out.println("sort...List...");
		Collections.sort(materials,  new SortByHotDegree());
		return materials;
	}
}

class SortByHotDegree implements Comparator<Material> {
	public int compare(Material m1, Material m2){
		if(m1.getMid() > m2.getMid()){			//按照id升序
			return 1;
		} else {
			return -1;
		}
	}
}
