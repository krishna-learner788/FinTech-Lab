package com.MIT.FIS10.controller.customer;

import com.MIT.FIS10.entity.customer.*;
import com.MIT.FIS10.service.customer.*;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerDetailService customerDetailService;

    @Autowired
    private CustomerNameService customerNameService;

    @Autowired
    private CustomerProofOfIdentityService proofOfIdentityService;

    @Autowired
    private CustomerContactDetailService contactDetailService;

    @Autowired
    private CustomerAddressService addressService;

    // ==================== LANDING PAGE ====================

    @GetMapping("")
    public String landingPage(Model model) {
        model.addAttribute("totalCustomers", customerDetailService.getAllCustomers().size());
        model.addAttribute("totalNames", customerNameService.getAll().size());
        model.addAttribute("totalProofs", proofOfIdentityService.getAll().size());
        model.addAttribute("totalContacts", contactDetailService.getAll().size());
        model.addAttribute("totalAddresses", addressService.getAll().size());
        return "customer/landing";
    }

    // ==================== CUSTOMER DETAIL SCREENS ====================

    @GetMapping("/details")
    public String listCustomerDetails(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 5;
        Page<CustomerDetail> customerPage = customerDetailService.getPaginatedCustomers(page - 1, pageSize);
        model.addAttribute("customers", customerPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", customerPage.getTotalPages());
        return "customer/customer-details";
    }

    @GetMapping("/details/new")
    public String showCreateCustomerForm(Model model) {
        model.addAttribute("customerDetail", new CustomerDetail());
        return "customer/customer-detail-form";
    }

    @PostMapping("/details/save")
    public String saveCustomerDetail(@Valid @ModelAttribute("customerDetail") CustomerDetail customer,
                                     BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "customer/customer-detail-form";
        }
        customerDetailService.saveCustomer(customer);
        return "redirect:/customers/details";
    }

    @GetMapping("/details/edit/{id}")
    public String showEditCustomerForm(@PathVariable Long id, Model model) {
        model.addAttribute("customerDetail", customerDetailService.findById(id));
        return "customer/customer-detail-form";
    }

    @PostMapping("/details/update/{id}")
    public String updateCustomerDetail(@PathVariable Long id,
                                       @Valid @ModelAttribute("customerDetail") CustomerDetail customer,
                                       BindingResult result) {
        if (result.hasErrors()) {
            return "customer/customer-detail-form";
        }
        customerDetailService.updateCustomer(id, customer);
        return "redirect:/customers/details";
    }

    @PostMapping("/details/delete/{id}")
    public String deleteCustomerDetail(@PathVariable Long id) {
        customerDetailService.deleteCustomerById(id);
        return "redirect:/customers/details";
    }

    // ==================== CUSTOMER NAME SCREENS ====================

    @GetMapping("/names")
    public String listCustomerNames(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 5;
        Page<CustomerName> namePage = customerNameService.getPaginated(page - 1, pageSize);
        model.addAttribute("names", namePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", namePage.getTotalPages());
        return "customer/customer-names";
    }

    @GetMapping("/names/new")
    public String showCreateNameForm(Model model) {
        model.addAttribute("customerName", new CustomerName());
        return "customer/customer-name-form";
    }

    @PostMapping("/names/save")
    public String saveCustomerName(@Valid @ModelAttribute("customerName") CustomerName name,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return "customer/customer-name-form";
        }
        customerNameService.save(name);
        return "redirect:/customers/names";
    }

    @GetMapping("/names/edit/{id}")
    public String showEditNameForm(@PathVariable Long id, Model model) {
        model.addAttribute("customerName", customerNameService.findById(id));
        return "customer/customer-name-form";
    }

    @PostMapping("/names/update/{id}")
    public String updateCustomerName(@PathVariable Long id,
                                     @Valid @ModelAttribute("customerName") CustomerName name,
                                     BindingResult result) {
        if (result.hasErrors()) {
            return "customer/customer-name-form";
        }
        customerNameService.update(id, name);
        return "redirect:/customers/names";
    }

    @PostMapping("/names/delete/{id}")
    public String deleteCustomerName(@PathVariable Long id) {
        customerNameService.deleteById(id);
        return "redirect:/customers/names";
    }

    // ==================== CUSTOMER PROOF OF IDENTITY SCREENS ====================

    @GetMapping("/proofs")
    public String listProofs(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 5;
        Page<CustomerProofOfIdentity> proofPage = proofOfIdentityService.getPaginated(page - 1, pageSize);
        model.addAttribute("proofs", proofPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", proofPage.getTotalPages());
        return "customer/customer-proofs";
    }

    @GetMapping("/proofs/new")
    public String showCreateProofForm(Model model) {
        model.addAttribute("proof", new CustomerProofOfIdentity());
        return "customer/customer-proof-form";
    }

    @PostMapping("/proofs/save")
    public String saveProof(@Valid @ModelAttribute("proof") CustomerProofOfIdentity proof,
                            BindingResult result) {
        if (result.hasErrors()) {
            return "customer/customer-proof-form";
        }
        proofOfIdentityService.save(proof);
        return "redirect:/customers/proofs";
    }

    @GetMapping("/proofs/edit/{id}")
    public String showEditProofForm(@PathVariable Long id, Model model) {
        model.addAttribute("proof", proofOfIdentityService.findById(id));
        return "customer/customer-proof-form";
    }

    @PostMapping("/proofs/update/{id}")
    public String updateProof(@PathVariable Long id,
                              @Valid @ModelAttribute("proof") CustomerProofOfIdentity proof,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "customer/customer-proof-form";
        }
        proofOfIdentityService.update(id, proof);
        return "redirect:/customers/proofs";
    }

    @PostMapping("/proofs/delete/{id}")
    public String deleteProof(@PathVariable Long id) {
        proofOfIdentityService.deleteById(id);
        return "redirect:/customers/proofs";
    }

    // ==================== CUSTOMER CONTACT DETAILS SCREENS ====================

    @GetMapping("/contacts")
    public String listContacts(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 5;
        Page<CustomerContactDetail> contactPage = contactDetailService.getPaginated(page - 1, pageSize);
        model.addAttribute("contacts", contactPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", contactPage.getTotalPages());
        return "customer/customer-contacts";
    }

    @GetMapping("/contacts/new")
    public String showCreateContactForm(Model model) {
        model.addAttribute("contact", new CustomerContactDetail());
        return "customer/customer-contact-form";
    }

    @PostMapping("/contacts/save")
    public String saveContact(@Valid @ModelAttribute("contact") CustomerContactDetail contact,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "customer/customer-contact-form";
        }
        contactDetailService.save(contact);
        return "redirect:/customers/contacts";
    }

    @GetMapping("/contacts/edit/{id}")
    public String showEditContactForm(@PathVariable Long id, Model model) {
        model.addAttribute("contact", contactDetailService.findById(id));
        return "customer/customer-contact-form";
    }

    @PostMapping("/contacts/update/{id}")
    public String updateContact(@PathVariable Long id,
                                @Valid @ModelAttribute("contact") CustomerContactDetail contact,
                                BindingResult result) {
        if (result.hasErrors()) {
            return "customer/customer-contact-form";
        }
        contactDetailService.update(id, contact);
        return "redirect:/customers/contacts";
    }

    @PostMapping("/contacts/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactDetailService.deleteById(id);
        return "redirect:/customers/contacts";
    }

    // ==================== CUSTOMER ADDRESS SCREENS ====================

    @GetMapping("/addresses")
    public String listAddresses(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 5;
        Page<CustomerAddress> addressPage = addressService.getPaginated(page - 1, pageSize);
        model.addAttribute("addresses", addressPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", addressPage.getTotalPages());
        return "customer/customer-addresses";
    }

    @GetMapping("/addresses/new")
    public String showCreateAddressForm(Model model) {
        model.addAttribute("address", new CustomerAddress());
        return "customer/customer-address-form";
    }

    @PostMapping("/addresses/save")
    public String saveAddress(@Valid @ModelAttribute("address") CustomerAddress address,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "customer/customer-address-form";
        }
        addressService.save(address);
        return "redirect:/customers/addresses";
    }

    @GetMapping("/addresses/edit/{id}")
    public String showEditAddressForm(@PathVariable Long id, Model model) {
        model.addAttribute("address", addressService.findById(id));
        return "customer/customer-address-form";
    }

    @PostMapping("/addresses/update/{id}")
    public String updateAddress(@PathVariable Long id,
                                @Valid @ModelAttribute("address") CustomerAddress address,
                                BindingResult result) {
        if (result.hasErrors()) {
            return "customer/customer-address-form";
        }
        addressService.update(id, address);
        return "redirect:/customers/addresses";
    }

    @PostMapping("/addresses/delete/{id}")
    public String deleteAddress(@PathVariable Long id) {
        addressService.deleteById(id);
        return "redirect:/customers/addresses";
    }
}
