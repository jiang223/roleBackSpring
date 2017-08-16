package com.util;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by a9805943 on 2017/4/22.
 *
 */
@Controller
public class ExtTree {
    Gson gson = new Gson();
    @ResponseBody
    @RequestMapping(value = "menuTreeJson")
    public Map<String,Object> menuTreeJson(){
        Map<String,Object>map=gson.fromJson(getTreeString(),Map.class);
        return map;
    }
    public String getTreeString(){
        String jsonString="{\n" +
                "        \"navItems\": {\n" +
                "            \"type\": \"treev\",\n" +
                "            \"root\": {\n" +
                "                \"expanded\": true,\n" +
                "                \"children\": [{\n" +
                "                    \"text\": \"Home\",\n" +
                "                    \"iconCls\": \"x-fa fa-home\",\n" +
                "                    \"children\": [{\n" +
                "                        \"text\": \"Messages\",\n" +
                "                        \"iconCls\": \"x-fa fa-inbox\",\n" +
                "                        \"leaf\": true\n" +
                "                    },{\n" +
                "                        \"text\": \"Archive\",\n" +
                "                        \"iconCls\": \"x-fa fa-database\",\n" +
                "                        \"children\": [{\n" +
                "                            \"text\": \"First\",\n" +
                "                            \"iconCls\": \"x-fa fa-sliders\",\n" +
                "                            \"leaf\": true\n" +
                "                        },{\n" +
                "                            \"text\": \"No Icon\",\n" +
                "                            \"iconCls\": null,\n" +
                "                            \"leaf\": true\n" +
                "                        }]\n" +
                "                    },{\n" +
                "                        \"text\": \"Music\",\n" +
                "                        \"iconCls\": \"x-fa fa-music\",\n" +
                "                        \"leaf\": true\n" +
                "                    },{\n" +
                "                        \"text\": \"Video\",\n" +
                "                        \"iconCls\": \"x-fa fa-film\",\n" +
                "                        \"leaf\": true\n" +
                "                    }]\n" +
                "                },{\n" +
                "                    \"text\": \"Users\",\n" +
                "                    \"iconCls\": \"x-fa fa-user\",\n" +
                "                    \"children\": [{\n" +
                "                       \"text\": \"Tagged\",\n" +
                "                        \"iconCls\": \"x-fa fa-tag\",\n" +
                "                        \"leaf\": true\n" +
                "                    },{\n" +
                "                        \"text\": \"Inactive\",\n" +
                "                        \"iconCls\": \"x-fa fa-trash\",\n" +
                "                        \"leaf\": true\n" +
                "                    }]\n" +
                "                },{\n" +
                "                    \"text\": \"Groups\",\n" +
                "                    \"iconCls\": \"x-fa fa-group\",\n" +
                "                    \"leaf\": true\n" +
                "                },{\n" +
                "                    \"text\": \"Settings\",\n" +
                "                    \"iconCls\": \"x-fa fa-wrench\",\n" +
                "                    \"children\": [{\n" +
                "                        \"text\": \"Sharing\",\n" +
                "                        \"iconCls\": \"x-fa fa-share-alt\",\n" +
                "                        \"leaf\": true\n" +
                "                    },{\n" +
                "                        \"text\": \"Notifications\",\n" +
                "                        \"iconCls\":\"x-fa fa-flag\",\n" +
                "                        \"leaf\": true\n" +
                "                    },{\n" +
                "                        \"text\": \"Network\",\n" +
                "                        \"iconCls\": \"x-fa fa-signal\",\n" +
                "                        \"leaf\": true\n" +
                "                    }]\n" +
                "                }]\n" +
                "            }\n" +
                "        }\n" +
                "    }";
        return jsonString;
    }
}
