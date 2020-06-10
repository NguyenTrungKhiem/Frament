package vn.ntu.edu.TrungKhiem.frament;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vn.ntu.edu.TrungKhiem.controller.ICartController;
import vn.ntu.edu.TrungKhiem.model.Product;


/**
 * A simple {@link Fragment} subclass.
 */
public class second_fragment extends Fragment implements View.OnClickListener{

    TextView txtCart;
    Button btnSubmit, btnCancel;
    List<Product> shoppingCart;
    ICartController controller;

    public second_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.second_fragment, container, false);

        controller = (ICartController) this.getActivity().getApplication();
        shoppingCart = controller.listShoppingCart();

        txtCart = rootView.findViewById(R.id.txtCart);
        btnSubmit = rootView.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
        btnCancel = rootView.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);

        String str = "";
        for(Product p : shoppingCart) {
            str += p.getName() + "\t\t\t" + p.getPrice() + "\n";
        }
        if(shoppingCart.isEmpty()) {
            str = "Bạn chưa có mặt hàng nào trong giỏ hàng cả";
        }
        txtCart.setText(str);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btnSubmit:
                btnSubmitOnClick();
                break;
            case R.id.btnCancel:
                btnCancelOnClick();
                break;
        }
    }

    private void btnSubmitOnClick() {
        String result = "Bạn chưa có mặt hàng nào trong giỏ hàng cả";
        if(!shoppingCart.isEmpty()) {
            result = "Bạn đã thanh toán thành công";
            controller.emptyCart();
        }
        Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
        txtCart.setText("Giỏ hàng rỗng");
    }

    private void btnCancelOnClick() {
        NavController navController = NavHostFragment.findNavController(second_fragment.this);
        navController.navigate(R.id.action_second_fragmet_to_first_fragment);
    }
}
