//package com.dream.yuxiaor.controller.smart.baozugong;
//
//import com.dream.yuxiaor.service.baozu.BaozuDeviceService;
//import com.dream.yuxiaor.utils.NumbericUtil;
//import com.yuxiaor.pms.yuxiaorapi.api.model.smartdevice.res.BaozuDeviceSearchRes;
//import com.yuxiaor.pms.yuxiaorapi.api.util.DateUtil;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.SneakyThrows;
//import lombok.ToString;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
//import static com.yuxiaor.pms.applib.core.utils.CompareUtils.areEquals;
//
//
//@Slf4j
//@RestController
//@RequestMapping(path = "open/ai/with")
//public class OpenBaozuController {
//
//    @Resource
//    //定义私有属性，类型为baozudeviceservice对象类型
//    private BaozuDeviceService baozuDeviceService;
//
//    @SneakyThrows
//    @PostMapping(path = "/device-info")
//    //ResponseEntity构造响应体，泛型是MessageResponse
//    public ResponseEntity<MessageResponse> getDeviceInfo(@RequestBody DeviceRequest req) {
//        log.info("getDeviceInfo req: {}", req);
//
//        // 基础手动校验
//        if (req == null || req.getDeviceId() == null || req.getDeviceType() == null) {
//            return ResponseEntity
//                    .status(HttpStatus.BAD_REQUEST)
//                    .body(new MessageResponse("deviceId 或 deviceType 缺失"));
//        }
//
//        Long companyId = 0L;
//        BaozuDeviceSearchRes deviceDetail = baozuDeviceService.deviceDetail(companyId, req.getDeviceId(), req.getDeviceType());
//        String deviceInfo = deviceInfoToString(deviceDetail);
//
//        String msg = "getDeviceInfo 已接收设备：id=" + req.getDeviceId() + ", type=" + req.getDeviceType() + ", result=" + deviceInfo;
//        log.info(msg);
//
//        return ResponseEntity.ok(new MessageResponse(deviceInfo));
//    }
//
//    private String deviceInfoToString(BaozuDeviceSearchRes deviceDetail) {
//        if(deviceDetail == null) {
//            return "暂无设备信息";
//        }
//        String result = commonDeviceInfo(deviceDetail);
//        switch (deviceDetail.getDeviceType()) {
//            case 1:
//                result +=  (deviceDetail.getLockPower() == null ? "" : "门锁电池电量: "+ deviceDetail.getLockPower());
//                break;
//            case 2:
//                result = result +
//                        "用量: " + NumbericUtil.formatToYunWithFen(deviceDetail.getScale()) + ", \\n" +
//                        "剩余用量: " + NumbericUtil.formatToYunWithFen(deviceDetail.getRemainingScale()) + ", \\n" +
//                        "闸门状态: " + Formatter.mapTripState(deviceDetail.getTripState()) + ", \\n" +
//                        "付费方式: " + Formatter.mapPayMode(deviceDetail.getPayMode()) + ", \\n" +
//                        "已透支用量: " + NumbericUtil.formatToYunWithFen(deviceDetail.getOverdraftScale()) + ", \\n" +
//                        "电量: " + deviceDetail.getPower() + ", \\n" +
//                        (deviceDetail.getAbnormalType() == null ? "" : "异常类型: " + deviceDetail.getAbnormalType() + ", \\n") +
//                        (StringUtils.isEmpty(deviceDetail.getVoltage()) ? "" : "电压: " + deviceDetail.getVoltage() + ", \\n") ;
//                break;
//            case 3:
//            case 7:
//                result = result +
//                        "用量: " + NumbericUtil.formatToYunWithFen(deviceDetail.getScale()) + ", \\n" +
//                        "剩余用量: " + NumbericUtil.formatToYunWithFen(deviceDetail.getRemainingScale()) + ", \\n" +
//                        "闸门状态: " + Formatter.mapTripState(deviceDetail.getTripState()) + ", \\n" +
//                        "付费方式: " + Formatter.mapPayMode(deviceDetail.getPayMode()) + ", \\n" +
//                        "已透支用量: " + NumbericUtil.formatToYunWithFen(deviceDetail.getOverdraftScale()) + ", \\n" +
//                        "电量: " + deviceDetail.getPower() + ", \\n" +
//                        "异常类型: " + deviceDetail.getAbnormalType() + ", \\n" +
//                        "水表电池电量: "+ deviceDetail.getLockPower() + ", \\n";
//                break;
//        }
//        return result;
//
//    }
//
//    private String commonDeviceInfo(BaozuDeviceSearchRes deviceDetail) {
//        return "设备ID: " + deviceDetail.getId() + ", \\n" +
//                "设备类型: " + Formatter.mapDeviceType(deviceDetail.getDeviceType()) + ", \\n" +
//                (StringUtils.isEmpty(deviceDetail.getModel()) ? "": "设备型号: " + deviceDetail.getModel() + ", \\n") +
//                "供应商编码: " + deviceDetail.getProviderCode() + ", \\n" +
//                "品牌名称: " + deviceDetail.getBrandName() + ", \\n" +
//                "抄表时间: " + DateUtil.formDate(deviceDetail.getReadTime(), DateUtil.DATETIME_FORMAT_SF) + ", \\n" +
//                "配置方式: " + Formatter.mapSettingMethod(deviceDetail.getSettingMethod()) + ", \\n" +
//                (deviceDetail.getSignalValue() == null ? "" : "信号值:" + deviceDetail.getSignalValue() + Conts.SIGNAL_DESC + ", \\n") +
//                "在线状态: " + Formatter.mapOnoffline(deviceDetail.getOnoffline()) + ", \\n" +
//                "蓝牙状态: " + Formatter.mapBluetooth(deviceDetail.getBluetooth()) +", \\n" +
//                (StringUtils.isEmpty(deviceDetail.getRomVer()) ? "" : "设备硬件版本号: " + (deviceDetail.getRomVer()) + ", \\n") +
//                "最后在线时间: "+ deviceDetail.getLastOnlineTime() + ", \\n";
//    }
//
//
//    @Data
//    @ToString
//    public static class DeviceRequest {
//        private Long deviceId;
//        private Integer deviceType;
//        private Long userId;
//
//    }
//
//    @Data
//    @AllArgsConstructor
//    public static class MessageResponse {
//        private String message;
//    }
//
//    static class Conts {
//        public static final String SIGNAL_DESC = " 备注：数值 0 表示最佳信号；数值越大，信号越好；当数值 小于 -85 时，一般认为小于-85表示信号较差。";
//    }
//}
//
//class Formatter {
//    public static String mapDeviceType(Integer deviceType) {
//        switch (deviceType) {
//            case 1:
//                return "门锁";
//            case 2:
//                return "电表";
//            case 3:
//                return "冷水表";
//            case 7:
//                return "热水表";
//            default:
//                return "未知类型";
//        }
//    }
//
//    public static String mapTripState(Integer tripState) {
//        if (tripState == null) {
//            return "";
//        }
//        switch (tripState) {
//            case 1:
//                return "合阀";
//            case 2:
//                return "跳阀";
//            default:
//                return "未知状态";
//        }
//    }
//
//    public static String mapPayMode(Integer payMode) {
//        if (payMode == null) {
//            return "";
//        }
//        switch (payMode) {
//            case 1:
//                return "预付费";
//            case 2:
//                return "后付费";
//            default:
//                return "未知方式";
//        }
//    }
//
//    public static String mapSettingMethod(Integer settingMethod) {
//        if (settingMethod == null) {
//            return "";
//        }
//        switch (settingMethod) {
//            case 0:
//                return "蓝牙";
//            case 1:
//                return "远程";
//            default:
//                return "未知方式";
//        }
//
//    }
//
//
//    public static String mapOnoffline(Integer onoffline) {
//        if (onoffline == null) {
//            return "";
//        }
//        switch (onoffline) {
//            case 1:
//                return "在线";
//            case 2:
//                return "离线";
//            default:
//                return "未知状态";
//        }
//    }
//
//    public static String mapBluetooth(Integer bluetooth) {
//        if (bluetooth == null) {
//            return "";
//        }
//        if (areEquals(bluetooth,1)) {
//            return "开启";
//        } else {
//            return "关闭";
//        }
//    }
//
//
//}
