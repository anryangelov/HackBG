package dependencies_resolving;

import java.util.HashMap;
import java.io.FileReader;
import java.io.File;

import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.Iterator;

public class DependenciesInstaller {

	private HashMap<String, ArrayList<String>> allDep;
	private ArrayList<String> installDep = new ArrayList<>();
	private ArrayList<String> localDep;	
	private String installDir;

	public DependenciesInstaller(String filePathAllPackages) {
		parseJSONFileAllDep(filePathAllPackages);
	}

	private void parseJSONFileAllDep(String filePath) {
		try {
			ContainerFactory containerFactory = new ContainerFactory() {
				public ArrayList<String> creatArrayContainer() {
					return new ArrayList<String>();
				}

				public HashMap<String, ArrayList<String>> createObjectContainer() {
					return new HashMap<>();
				}
			};
			JSONParser parser = new JSONParser();
			allDep = (HashMap) parser.parse(new FileReader(filePath),
					containerFactory);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addLocalDeb(String filePath) {
		try {
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser
					.parse(new FileReader(filePath));
			localDep = (ArrayList<String>) obj.get("dependencies");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addInstallDir(String dirPath) {
		installDir = dirPath;
		File dir = new File(installDir);
		String[] files = dir.list();
		String name;
		for (int i = 0; i < files.length; i++) {
			name = files[i];
			installDep.add(name);
		}
	}

	private void resolveRecursivly(String lib, String spaces) {
		ArrayList<String> libDeps = allDep.get(lib);
		System.out.println(spaces + "Installing " + lib);
		if (!installDep.contains(lib)) {
			System.out.print(spaces + "In order to install " + lib + " we need from " + libDeps);
			System.out.println();
			if (libDeps.size() > 0) {
				for (String libDep : libDeps) {
					resolveRecursivly(libDep, spaces + "    ");
				}
			}
			installResolvedDep(lib);
			System.out.println(spaces + lib + " is installed" );
		} else {
			System.out.println(spaces + lib + " is already installed");
		}
	}

	public void install() {
		for (String dep : localDep) {
			resolveRecursivly(dep, "");
		}
	}


    private void installResolvedDep(String lib) {
    	File f = new File(installDir, lib);
    	try {
    		f.createNewFile();
      	} catch (Exception e) {
      		e.printStackTrace();
      	}
  	    installDep.add(lib);
  	}
    	
	
	public ArrayList<String> listInstalledDep() {
		return installDep;
	}
	

	public void show() {
		Iterator entries = allDep.entrySet().iterator();
		while (entries.hasNext()) {
			HashMap.Entry entry = (HashMap.Entry) entries.next();
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
		for (String temp : localDep) {
			System.out.println(temp);
		}
		for (String tem : installDep) {
			System.out.println(tem);
		}
	}
	
	public static void main(String[] args) {
		DependenciesInstaller dr = new DependenciesInstaller("allDep.json");
		dr.addLocalDeb("localDep.json");
		dr.addInstallDir("installedDependencies");
		dr.install();
		System.out.println(dr.listInstalledDep());
	}
}
