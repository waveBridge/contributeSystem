package cn.cs.util;

import java.util.List;

import cn.cs.entity.Material;

public class Redundant {
	public static List<Material> redundant(List<Material> materials){
		System.out.println("Redundant...");
		try{
			for(Material m : materials){
				m.getUser().setMaterialSet(null);
			}
			return materials;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
}
