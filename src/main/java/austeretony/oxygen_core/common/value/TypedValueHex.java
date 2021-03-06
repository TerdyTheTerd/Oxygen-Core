package austeretony.oxygen_core.common.value;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import austeretony.oxygen_core.common.EnumValueType;
import austeretony.oxygen_core.common.util.StreamUtils;
import io.netty.buffer.ByteBuf;

public class TypedValueHex implements TypedValue<Integer> {

    protected int value;

    public TypedValueHex(int initial) {
        this.value = initial;
    }

    @Override
    public EnumValueType getType() {
        return EnumValueType.HEX;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public void init(Integer value) {
        this.value = value;
    }

    @Override
    public void fromString(String str) {
        this.value = (int) Long.parseLong(str, 16);
    }

    @Override
    public String toString() {
        return Integer.toHexString(this.value);
    }

    @Override
    public void fromJson(JsonElement valueElement) {
        this.value = (int) Long.parseLong(valueElement.getAsString(), 16);
    }

    @Override
    public JsonElement toJson() {
        return new JsonPrimitive(this.toString());
    }

    @Override
    public void write(BufferedOutputStream bos) throws IOException {
        StreamUtils.write(this.value, bos);
    }

    @Override
    public void read(BufferedInputStream bis) throws IOException {
        this.value = StreamUtils.readInt(bis);
    }

    @Override
    public void write(ByteBuf buffer) {
        buffer.writeInt(this.value);
    }

    @Override
    public void read(ByteBuf buffer) {
        this.value = buffer.readInt();
    } 
}
