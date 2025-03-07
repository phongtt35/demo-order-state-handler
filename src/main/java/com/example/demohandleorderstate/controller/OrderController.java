package com.example.demohandleorderstate.controller;

import com.example.demohandleorderstate.model.Order;
import com.example.demohandleorderstate.model.OrderHistory;
import com.example.demohandleorderstate.model.OrderState;
import com.example.demohandleorderstate.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public String viewOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        Map<Long, List<String>> orderActions = new HashMap<>();
        for (Order order : orders) {
            orderActions.put(order.getId(), getAvailableActions(order.getState()));
        }
        model.addAttribute("orders", orders);
        model.addAttribute("orderActions", orderActions);
        return "orders";
    }

    @GetMapping("/{orderId}/history")
    public String viewOrderHistory(@PathVariable Long orderId, Model model) {
        List<OrderHistory> orderHistory = orderService.getOrderHistory(orderId);
        model.addAttribute("orderHistory", orderHistory);
        model.addAttribute("selectedOrderId", orderId);
        return "order-history";
    }

    @PostMapping("/{orderId}/confirm")
    public String confirmOrder(@PathVariable Long orderId) {
        orderService.confirmOrder(orderId);
        return "redirect:/orders";
    }

    @PostMapping("/{orderId}/cancel")
    public String cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }

    @PostMapping("/{orderId}/hold")
    public String waitForInput(@PathVariable Long orderId) {
        orderService.waitForInput(orderId);
        return "redirect:/orders";
    }

    @PostMapping("/{orderId}/prepare")
    public String prepareOrder(@PathVariable Long orderId) {
        orderService.prepareOrder(orderId);
        return "redirect:/orders";
    }

    @PostMapping("/{orderId}/ship")
    public String shipOrder(@PathVariable Long orderId) {
        orderService.shipOrder(orderId);
        return "redirect:/orders";
    }

    @PostMapping("/{orderId}/complete")
    public String completeOrder(@PathVariable Long orderId) {
        orderService.completeOrder(orderId);
        return "redirect:/orders";
    }

    @PostMapping("/{orderId}/ghost")
    public String ghostOrder(@PathVariable Long orderId) {
        orderService.ghostOrder(orderId);
        return "redirect:/orders";
    }

    @PostMapping("/{orderId}/reportLost")
    public String reportLostOrder(@PathVariable Long orderId) {
        orderService.reportLostOrder(orderId);
        return "redirect:/orders";
    }

    @PostMapping("/{orderId}/return")
    public String returnOrder(@PathVariable Long orderId) {
        orderService.returnOrder(orderId);
        return "redirect:/orders";
    }

    private List<String> getAvailableActions(OrderState state) {
        return switch (state) {
            case CHO_XAC_NHAN -> List.of("confirm", "hold", "cancel");
            case DA_XAC_NHAN, CHO_NHAP_HANG -> List.of("prepare", "cancel");
            case DANG_CHUAN_BI -> List.of("ship", "cancel");
            case DANG_GIAO_HANG -> List.of("complete", "ghost");
            case GIAO_THAT_BAI -> List.of("reportLost", "return");
            default -> List.of();
        };
    }
}