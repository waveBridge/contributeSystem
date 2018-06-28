package cn.cs.util;

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
				m.getUser().setMaterialSet(null);
				m.getClassify().setMaterialSet(null);
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

	public static void haveEmployed(Set<Material> materialSet) {
		System.out.println("haveEmployed...materialSet...");
		
		try{
			for(Material m : materialSet){
				if(m.getState() != 1){
					materialSet.remove(m);
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}
	
	public static void haveEmployed(List<Material> materialList) {
		System.out.println("haveEmployed...materialSet...");
		
		try{
			for(Material m : materialList){
				if(m.getState() != 1){
					materialList.remove(m);
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}

	public static void haveEmployed(Material material) {
		System.out.println("haveEmployed...material...");
		
		try{
			for(Material m : material.getUser().getMaterialSet()){
				if(m.getState() != 1){
					material.getUser().getMaterialSet().remove(m);
				}
			}
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
