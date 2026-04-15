package com.novibe.dns.next_dns.http;

import com.novibe.dns.next_dns.http.dto.request.CreateDenyDto;
import com.novibe.dns.next_dns.http.dto.response.deny.DenyDto;
import com.novibe.dns.next_dns.http.dto.response.deny.MultiDenyResponse;
import com.novibe.dns.next_dns.http.dto.response.deny.SingleDenyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NextDnsDenyClient extends AbstractNextDnsHttpClient {

    public List<DenyDto> fetchDenylist() {
        return get(path(), MultiDenyResponse.class)
                .getData();
    }

    public SingleDenyResponse saveDeny(CreateDenyDto rewriteDto) {
        return post(path(), rewriteDto, SingleDenyResponse.class);
    }


    public SingleDenyResponse deleteDenyById(String id) {
        return delete(path() + "/" + id, SingleDenyResponse.class);
    }

    @Override
    protected String path() {
        return "/denylist";
    }

}
