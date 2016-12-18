package com.org.iii.menuapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private String[] menu_group = {"麵", "飯", "湯"};
    private String[][] menu_sub = {{ "炒麵", "義大利麵" }, { "炒飯", "咖哩飯", "滷肉飯" }, {"菜頭湯", "葛利湯"}};
    private int[][] price = {{1, 2}, {3, 4, 5}, {6, 7}};
    private int[] img = {R.drawable.dish0, R.drawable.dish1, R.drawable.dish2};
    private List<Map<String, String>> group = new ArrayList<>();
    private List<List<Map<String, String>>> childrenList = new ArrayList<>();
    private ExpandableListView expListView;
    private Context context;
    private Bitmap bitmap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        expListView = (ExpandableListView)findViewById(R.id.expListView);

        // 將 menu_group & menu_sub 資料拆解並配置到 group & childrenList
        for (int i = 0; i< menu_group.length ; i++ ) {
            // Group
            Map<String, String> groupFood = new HashMap<>();
            groupFood.put("groupKey", menu_group[i]);
            group.add(groupFood);

            // Sub
            List<Map<String, String>> children = new ArrayList<>();
            for (String food : menu_sub[i]) {
                Map<String, String> childMap = new HashMap<>();
                childMap.put("childKey", food);
                children.add(childMap);
            }

            childrenList.add(children);
        }

        // 建立 SimpleExpandableListAdapter
        SimpleExpandableListAdapter mAdapter =
                new SimpleExpandableListAdapter(
                        context,
                        group,
                        android.R.layout.simple_expandable_list_item_1,
                        new String[] { "groupKey" },
                        new int[] { android.R.id.text1 },
                        childrenList,
                        android.R.layout.simple_expandable_list_item_1,
                        new String[] { "childKey" },
                        new int[] { android.R.id.text1  }
                );

        // 設定Adapter
        expListView.setAdapter(mAdapter);

        // 設定監聽器
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent,
                                        View view, int groupPosition, int childPosition, long id) {

                String mainfood = menu_group[groupPosition];
                String subfood = menu_sub[groupPosition][childPosition];
                Toast.makeText(context, mainfood + ":" + subfood,
                        Toast.LENGTH_SHORT).show();
                bitmap = BitmapFactory.decodeResource(getResources(), img[2]);
//                int test = bitmap.getByteCount();
//                Log.v("will", "byteSize"+test);

                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,bs);

                Intent it = new Intent(MainActivity.this, Main2Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("meal", subfood);
//                it.putExtra("meal", subfood);
                bundle.putInt("price", price[0][0]);
//                it.putExtra("price", price[0][1]);
                bundle.putByteArray("img", bs.toByteArray());
//                it.putExtra("img", bs.toByteArray());
                it.putExtras(bundle);
                startActivity(it);

                return false;
            }

        });

    }


}
