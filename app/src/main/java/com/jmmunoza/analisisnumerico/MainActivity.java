package com.jmmunoza.analisisnumerico;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.jmmunoza.analisisnumerico.util.ToastMaker;
import com.jmmunoza.analisisnumerico.view.fragmentmanager.FragmentAdderManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToastMaker.init(this);
        if(savedInstanceState == null){
            FragmentAdderManager.init(this);
        } else {
            FragmentAdderManager.reload(this);
        }

        /*

        if(!Python.isStarted()){
            Python.start(new AndroidPlatform(this));
        }

        String code = "";
        code += "import numpy as np\n" +
                "t = np.linspace(0, 4*np.pi, 100)\n" +
                "y = np.cos(t) # Función original\n" +
                "kdy = -np.sin(t) # Derivada simbólica\n" +
                "dy = np.diff(y)/np.diff(t) # Derivada numérica\n" +
                "print(dy)";

        Python py                 = Python.getInstance();
        PyObject sys              = py.getModule("sys");
        PyObject io               = py.getModule("io");
        PyObject console          = py.getModule("interpreter");
        PyObject textOutputStream = io.callAttr("StringIO");
        sys.put("stdout", textOutputStream);

        String interpreterOutput = "";
        try {
            console.callAttrThrows("mainTextCode", code);
            interpreterOutput = textOutputStream.callAttr("getvalue").toString();
        }catch (PyException e){
            interpreterOutput = e.getMessage();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.out.println(interpreterOutput);

         */
    }

    @Override
    public void onBackPressed() {
        FragmentAdderManager.onBackPressed();
    }
}