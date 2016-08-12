package fragment;

import java.util.ArrayList;

import model.ModelUI;

import com.example.warehouse.R;

import activity.AnythingActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class WeFragment extends Fragment {

	private ArrayList<Button> mButtons;
	
	private TextView weText;
	
    public WeFragment(){
        super();
    }

    /**
     * ���Ǵ˺�������ͨ��inflater inflate�����õ�view��󷵻�
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
        View v = inflater.inflate(R.layout.we_fragment, container, false);
        weText = (TextView)v.findViewById(R.id.we_text);
        weText.setText("Ŀǰ��Ӧ�ò�������ʹ�ã������ڴ���");
        return v;
    }
	
}
