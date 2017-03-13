package stlabs.triangle.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import StoneLabs.sutil.Debug;

public class ResourceLoader 
{
	public static Shader loadShader(String vsPath, String fsPath)
	{
		Shader shader = new Shader();
		try 
		{
			String workingDirectory = System.getProperty("user.dir");
			Debug.Log("Loading shader... [WD: " + workingDirectory + "]");
			shader.addVertexShader(new String(Files.readAllBytes(Paths.get(workingDirectory + "/res/" + vsPath))));
			shader.addFragmentShader(new String(Files.readAllBytes(Paths.get(workingDirectory + "/res/" + fsPath))));
		} 
		catch (IOException e) 
		{
			Debug.Error(e.getMessage());
		}
		shader.compileShader();
		return shader;
	}
}
