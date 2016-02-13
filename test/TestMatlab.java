package matlabAutomata;

import java.io.File;
import java.util.Arrays;

import matlabcontrol.*;
import matlabcontrol.extensions.MatlabNumericArray;
import matlabcontrol.extensions.MatlabTypeConverter;

public class TestMatlab {
	
	public void runMatlab(File mFile) throws MatlabConnectionException, MatlabInvocationException{
	
		
		//Create a proxy, which we will use to control MATLAB
        MatlabProxyFactoryOptions options = new MatlabProxyFactoryOptions.Builder().setUsePreviouslyControlledSession(true).build();
        MatlabProxyFactory factory = new MatlabProxyFactory(options);
        MatlabProxy proxy = factory.getProxy();

        String dir = mFile.getParentFile().getAbsolutePath();
        String cmd_path = "addpath " + dir;
        proxy.eval(cmd_path);
        
        Object[] averageReturn = proxy.returningFeval("my_avg", 1, "3:8");
        System.out.println("Average Return");
        System.out.println(Arrays.toString((String[]) averageReturn[1]));
            
      
        //Disconnect the proxy from MATLAB
        proxy.disconnect();
			
	}
	
	
    public static void main(String[] args) throws MatlabConnectionException, MatlabInvocationException {
    	 if (0 < args.length) {
    	      File mFile = new File(args[0]);
    	      TestMatlab test = new TestMatlab();
    	      test.runMatlab(mFile); 
    	 } else {
    		   System.err.println("Supply Matlab M file");
    		   System.exit(0);
    		}
    }
}