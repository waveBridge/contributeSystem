package cn.cs.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.cs.entity.Material;
import cn.cs.entity.User;

public class Redundant {
	public static List<Material> redundant(List<Material> materials){
		System.out.println("Redundant...MaterialList...");
		try{
			for(Material m : materials){
				m.getUser().setMaterialSet(null);
				m.getClassify().setMaterialSet(null);
			}
			return materials;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}

	public static Set<Material> redundant(Set<Material> materials) {
		System.out.println("Redundant...MaterialSet...");
		try{
			for(Material m : materials){
				m.setClassify(null);
				m.setUser(null);
			}
			return materials;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
	
	public static void redundant(Material material) {
		System.out.println("Redundant...Material...");
		
		try{
			material.setClassify(null);
			for(Material m : material.getUser().getMaterialSet()){
				if(m != null){
					m.setClassify(null);
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public static Set<Material> haveEmployed(Set<Material> materialSet) {
		System.out.println("haveEmployed...materialSet...");
		
		try{
			Set<Material> materials = new HashSet<Material>();
		
			for(Material m : materialSet){
				if(m.getState() == 1){
					materials.add(m);
				}
			}
			
			return materials;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
	
	public static List<Material> haveEmployed(List<Material> materialList) {
		System.out.println("haveEmployed...materialList...");
		
		try{
			List<Material> materials = new ArrayList<Material>();
			for(Material m : materialList){
				if(m.getState() == 1){
					materials.add(m);
				}
			}
			return materials;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}

	public static void haveEmployed(Material material) {
		System.out.println("haveEmployed...material...");
		
		try{
			Set<Material> materials = new HashSet<Material>();
			for(Material m : material.getUser().getMaterialSet()){
				if(m.getState() == 1){
					materials.add(m);
				}
			}
			material.getUser().setMaterialSet(materials);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	//user去冗余
	public static User redundant(User user) {
		System.out.println("redundant...user...");
		
		try{
			user.setMaterialSet(null);
			user.setPassword(null);
			return user;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}




}
