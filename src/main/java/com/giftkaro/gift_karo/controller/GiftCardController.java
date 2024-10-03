package com.giftkaro.gift_karo.controller;

import com.giftkaro.gift_karo.dto.GiftCardDTO;
import com.giftkaro.gift_karo.service.GiftCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/giftcards")
@RequiredArgsConstructor
public class GiftCardController {

    private final GiftCardService giftCardService;

    // Endpoint for adding a gift card
    @PostMapping
    public ResponseEntity<GiftCardDTO> addGiftCard(@RequestBody GiftCardDTO giftCardDTO) {
        GiftCardDTO addedGiftCard = giftCardService.addGiftCard(giftCardDTO);

        // Return 201 Created with the location of the new gift card
        return ResponseEntity.created(URI.create("/api/v1/giftcards/" + addedGiftCard.getId()))
                .body(addedGiftCard);
    }

    // Endpoint to get gift cards by restaurant ID
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<GiftCardDTO>> getGiftCardsByRestaurant(@PathVariable Long restaurantId) {
        List<GiftCardDTO> giftCards = giftCardService.getGiftCardsByRestaurant(restaurantId);
        return ResponseEntity.ok(giftCards);
    }

    // Endpoint to get a gift card by ID
    @GetMapping("/{id}")
    public ResponseEntity<GiftCardDTO> getGiftCardById(@PathVariable Long id) {
        GiftCardDTO giftCard = giftCardService.getGiftCardById(id);
        return ResponseEntity.ok(giftCard);
    }

    // Endpoint to update a gift card by ID
    @PutMapping("/{id}")
    public ResponseEntity<GiftCardDTO> updateGiftCard(@PathVariable Long id, @RequestBody GiftCardDTO giftCardDTO) {
        GiftCardDTO updatedGiftCard = giftCardService.updateGiftCard(id, giftCardDTO);

        // Return 200 OK with the updated gift card
        return ResponseEntity.ok(updatedGiftCard);
    }

    // Endpoint to delete a gift card by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGiftCard(@PathVariable Long id) {
        giftCardService.deleteGiftCard(id);

        // Return 204 No Content after deletion
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/share")
    public ResponseEntity<String> shareGiftCard(
            @RequestParam Long senderId,
            @RequestParam Long recipientId,
            @RequestParam Long giftCardId,
            @RequestParam BigDecimal shareAmount) {

        giftCardService.shareGiftCard(senderId, recipientId, giftCardId, shareAmount);
        return ResponseEntity.ok("Gift card shared successfully!");
    }

}

